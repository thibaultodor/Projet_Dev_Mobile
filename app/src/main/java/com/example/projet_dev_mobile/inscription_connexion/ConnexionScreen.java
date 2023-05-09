package com.example.projet_dev_mobile.inscription_connexion;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.home.Home_Candidat;
import com.example.projet_dev_mobile.home.Home_Employeur;

public class ConnexionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int type_of_account = sharedPreferences.getInt("type_of_account", 0);

        //Toast.makeText(this, String.valueOf(type_of_account), Toast.LENGTH_SHORT).show();

        if(type_of_account == 1){
            Intent myIntent = new Intent(this, Home_Candidat.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        }
        else if(type_of_account == 2){
            Intent myIntent = new Intent(this, Home_Employeur.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_screen);

        Button bretour = findViewById(R.id.retourOffre_connexion);
        bretour.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, Home_Candidat.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        });
        Button bconnexion = findViewById(R.id.connexion_button);
        bconnexion.setOnClickListener(view -> {
            //Intent myIntent = new Intent(this, InscriptionJobSearcher_part1.class);
            //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //startActivity(myIntent);

            ///// HERE CHECK USER EXIST IN DATABASE /////
            boolean isExist = false;
            /////////////////////////////////////////////

            if(isExist) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                ////// En fonction du type de compte value différente //////
                editor.putInt("type_of_account", 1);
                ////////////////////////////////////////////////////////////
                editor.apply();
            }

        });
        Button binscription = findViewById(R.id.inscription_button_connexion);
        binscription.setOnClickListener(view -> {

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.type_of_account_dialogue_box);

            dialog.show();

            Button b_dialogue_ok = dialog.findViewById(R.id.dialogue_ok);
            b_dialogue_ok.setOnClickListener(v -> {
                Intent myIntent = new Intent(this, InscriptionJobSearcher_part1.class);

                RadioGroup radioGroup = dialog.findViewById(R.id.radio_group_choice);
                RadioButton checkedRadioButton = (RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId());
                myIntent.putExtra("choice_account",checkedRadioButton.getText());

                startActivity(myIntent);
            });
        });
    }
}