package com.example.projet_dev_mobile.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.inscription_connexion.ConnexionScreen;
import com.example.projet_dev_mobile.pdfTest.Pdf;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int type_of_account = sharedPreferences.getInt("type_of_account",0);

        Button bDeconnect = view.findViewById(R.id.deconnect);

        if(type_of_account == 0){
            bDeconnect.setVisibility(View.GONE);
        }
        else {
            bDeconnect.setVisibility(View.VISIBLE);
        }

        bDeconnect.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("type_of_account",0);
            editor.apply();
            Intent myIntent = new Intent(this.getContext(), ConnexionScreen.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        });

        Spinner spinner = view.findViewById(R.id.langSpinner);
        ArrayList<String> listLangue = new ArrayList<>();
        listLangue.add("Français");
        listLangue.add("Italien");
        listLangue.add("Anglais");
        listLangue.add("Allemand");
        listLangue.add("Espagnol");
        listLangue.add("Arabe");
        listLangue.add("Portugais");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listLangue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection here
                String selectedItem = parent.getItemAtPosition(position).toString();

                List<String> la = new ArrayList<>(7);
                la.add("fr");
                la.add("it");
                la.add("en");
                la.add("de");
                la.add("es");
                la.add("ar");
                la.add("pt");

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                int idUser = sharedPreferences.getInt("idUser",-1);
                DataBase.User.getUsers().setLanguage(idUser, la.get(position));
                DataBase d = DataBase.getInstance();d.setJsonUsers(getContext());
                Toast.makeText(getContext(), "Langue sélectionné : " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when no item is selected
            }
        });

        return view;
    }
}