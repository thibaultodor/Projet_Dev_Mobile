package com.example.projet_dev_mobile.ui.profil;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.pdfTest.Pdf;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class ProfilFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String json = sharedPreferences.getString("UserJson","");
        int type_of_account = sharedPreferences.getInt("type_of_account",0);

        //Toast.makeText(getContext(), String.valueOf(isConnected), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();

        Log.println(Log.DEBUG,"DEBUG",json);

        if(type_of_account == 1) {
            int idUser = sharedPreferences.getInt("idUser",-1);
            String[] data = DataBase.User.getUsers().get(DataBase.User.ID, String.valueOf(idUser));

            TextView tname = view.findViewById(R.id.user_name);
            tname.setText("Nom : " + data[DataBase.User.NOM]);

            TextView tprenom = view.findViewById(R.id.user_prenom);
            tprenom.setText("Prenom : " + data[DataBase.User.PRENOM]);

            TextView tnaissance = view.findViewById(R.id.user_datenaissance);
            tnaissance.setText("Date de naissance : " + data[DataBase.User.DATE_NAISSANCE]);

            TextView tnationalite = view.findViewById(R.id.user_nationalite);
            tnationalite.setText("Nationalité : " + data[DataBase.User.NATIONALITE]);

            TextView ttelephone = view.findViewById(R.id.user_phone);
            ttelephone.setText("Téléphone : " + data[DataBase.User.NUM_TEL]);

            TextView temail = view.findViewById(R.id.user_email);
            temail.setText("eMail : " + data[DataBase.User.MAIL]);

            TextView tville = view.findViewById(R.id.user_ville);
            tville.setText("Ville : " + data[DataBase.User.VILLE]);

            Button bCV = view.findViewById(R.id.voir_cv);
            bCV.setOnClickListener(v -> {
                Intent myIntent = new Intent(this.getContext(), Pdf.class);
                startActivity(myIntent);
            });

            Button bCVAdd = view.findViewById(R.id.import_cv);
            bCVAdd.setOnClickListener(v -> {
                openFileChooser();
            });

            Button change_mdp = view.findViewById(R.id.change_mdp);
            change_mdp.setOnClickListener(v -> {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Values");
                final EditText oldPass = new EditText(getActivity());
                final EditText newPass = new EditText(getActivity());
                final EditText confirmPass = new EditText(getActivity());


                oldPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                oldPass.setHint("Old Password");
                newPass.setHint("New Password");
                confirmPass.setHint("Confirm Password");
                LinearLayout ll=new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(oldPass);
                ll.addView(newPass);
                ll.addView(confirmPass);
                alertDialog.setView(ll);
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if(data[DataBase.User.MOT_DE_PASSE].equals(oldPass.getText().toString())){
                                    if(newPass.getText().toString().equals(confirmPass.getText().toString())){
                                        DataBase.User.getUsers().setMotDePasse(idUser,newPass.getText().toString());
                                        DataBase d = DataBase.getInstance();d.setJsonUsers(getContext());
                                        Toast.makeText(getContext(), "Mot de passe modifié avec succés", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getContext(), "Erreur lors de la confirmation du mot de passe", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getContext(), "Ancien mot de passe non valide", Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "Mot de passe de non modifié", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = alertDialog.create();
                alert11.show();
            });
        }

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 123);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            // Save the selected file in the assets folder or process it as needed
            try {
                saveFileToAssets(selectedFileUri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void saveFileToAssets(Uri fileUri) throws FileNotFoundException {
        // Get the file's display name using ContentResolver
        String fileName = getFileNameFromUri(fileUri);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);
        DataBase.User.getUsers().setCV(idUser,fileName);
        DataBase d = DataBase.getInstance();d.setJsonUsers(getContext());

        Toast.makeText(getActivity(),fileName, Toast.LENGTH_SHORT).show();

        InputStream inputStream = getContext().getContentResolver().openInputStream(fileUri);
        File outputFile = new File(getContext().getFilesDir(), fileName);  // Specify the desired output file name

        try {
            OutputStream outputStream = Files.newOutputStream(outputFile.toPath());
            byte[] buffer = new byte[4096];
            int bytesRead;
            while (true) {
                assert inputStream != null;
                if ((bytesRead = inputStream.read(buffer)) == -1) break;
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }
}