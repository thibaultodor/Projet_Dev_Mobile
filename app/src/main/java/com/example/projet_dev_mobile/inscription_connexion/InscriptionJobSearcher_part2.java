package com.example.projet_dev_mobile.inscription_connexion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.home.Home_Anonyme;
import com.example.projet_dev_mobile.userdata.User_Data_Candidat;
import com.example.projet_dev_mobile.userdata.User_Data_Other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class InscriptionJobSearcher_part2 extends AppCompatActivity {

    private String cvname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String choice = extras.getString("choice_account");

        if(choice.equals("Candidat")){
            setContentView(R.layout.inscription_job_searcher_candidat_part2);
            Button bvalid = findViewById(R.id.inscription_button);
            bvalid.setOnClickListener(view -> {
                User_Data_Candidat user = getIntent().getParcelableExtra("user");
                user.setCv(this.cvname);
                TextView commentaire = findViewById(R.id.commentaire_inscription);
                user.setCommentaire(commentaire.getText().toString());
                Intent myIntent = new Intent(this, SecurityCodeSend.class);
                myIntent.putExtra("user", user);
                myIntent.putExtra("choice_account", choice);
                startActivity(myIntent);
            });
            Button bretour = findViewById(R.id.retourOffre_inscription);
            bretour.setOnClickListener(view -> {
                Intent myIntent = new Intent(this, Home_Anonyme.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            });

            Button bCV = findViewById(R.id.cv_button);
            bCV.setOnClickListener(v -> openFileChooser());
        }
        else if (choice.equals("Employeur") || choice.equals("Agence d'intérim") || choice.equals("Gestionnaire")){
            setContentView(R.layout.inscription_job_searcher_other_part2);
            Button bvalid = findViewById(R.id.inscription_button);
            bvalid.setOnClickListener(view -> {
                boolean allfill = true;
                TextView num_tel1 = findViewById(R.id.num_tel_1);
                TextView num_tel2 = findViewById(R.id.num_tel_2);
                TextView adresse = findViewById(R.id.adresse);
                if (adresse.getText().toString().equals("")) {adresse.setHintTextColor(Color.RED);allfill = false;
                } else {adresse.setHintTextColor(Color.BLACK);}
                TextView site_web = findViewById(R.id.site_web);
                TextView linkedin = findViewById(R.id.linkedin);
                TextView facebook = findViewById(R.id.facebook);
                if (allfill) {
                    User_Data_Other user = getIntent().getParcelableExtra("user");
                    if (user != null) {
                        user.set_User_data_part2(String.valueOf(num_tel1.getText()),
                                String.valueOf(num_tel2.getText()),
                                String.valueOf(adresse.getText()),
                                String.valueOf(site_web.getText()),
                                String.valueOf(linkedin.getText()),
                                String.valueOf(facebook.getText()));
                    }
                    Intent myIntent = new Intent(this, SecurityCodeSend.class);
                    myIntent.putExtra("user", user);
                    myIntent.putExtra("choice_account", choice);
                    startActivity(myIntent);
                }
            });
            Button bretour = findViewById(R.id.retourOffre_inscription);
            bretour.setOnClickListener(view -> {
                Intent myIntent = new Intent(this, Home_Anonyme.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            });
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
        this.cvname = fileName;
        Toast.makeText(this,fileName, Toast.LENGTH_SHORT).show();

        InputStream inputStream = getContentResolver().openInputStream(fileUri);
        File outputFile = new File(getFilesDir(), fileName);  // Specify the desired output file name

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
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
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