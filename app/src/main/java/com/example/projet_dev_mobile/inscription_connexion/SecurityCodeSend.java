package com.example.projet_dev_mobile.inscription_connexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.home.Home_Candidat;
import com.example.projet_dev_mobile.home.Home_Employeur;
import com.example.projet_dev_mobile.userdata.User_Data_Candidat;
import com.example.projet_dev_mobile.userdata.User_Data_Other;
import com.google.gson.Gson;

public class SecurityCodeSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_code_send);

        Bundle extras = getIntent().getExtras();
        String choice = extras.getString("choice_account");

        Button bCheckCode = findViewById(R.id.check_code_sent);
        bCheckCode.setOnClickListener(view -> {
            ///// HERE CHECK USER CHECK SMS /////
            boolean isCheck = true;
            /////////////////////////////////////////////

            if(isCheck) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = getIntent();

                if(choice.equals("Candidat")) {
                    User_Data_Candidat user = intent.getParcelableExtra("user");
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    editor.putString("UserJson",json);
                    editor.apply();
                    editor.putInt("type_of_account",1);
                    editor.apply();
                    Intent myIntent = new Intent(this, Home_Candidat.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myIntent);
                }
                else if (choice.equals("Employeur") || choice.equals("Agence d'intérim") || choice.equals("Gestionnaire")){
                    User_Data_Other user = intent.getParcelableExtra("user");
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    editor.putString("UserJson",json);
                    editor.apply();
                    if(choice.equals("Employeur")) {
                        editor.putInt("type_of_account",2);
                        editor.apply();
                        Intent myIntent = new Intent(this, Home_Employeur.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(myIntent);
                    }
                }
            }
        });
    }
}