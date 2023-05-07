package com.example.projet_dev_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.userdata.User_Data;

public class ConnectionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        boolean connectedState = sharedPreferences.getBoolean("isConnected", false);

        Toast.makeText(getApplicationContext(), String.valueOf(connectedState), Toast.LENGTH_SHORT).show();

        if(connectedState){
            Intent myIntent = new Intent(this, Home.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_screen);

        Button bretour = findViewById(R.id.retourOffre_connexion);
        bretour.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, Home.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        });
        Button bconnexion = findViewById(R.id.connexion_button);
        bconnexion.setOnClickListener(view -> {
            //Intent myIntent = new Intent(this, InscriptionJobSearcher.class);
            //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //startActivity(myIntent);

            ///// HERE CHECK USER EXIST IN DATABASE /////
            boolean isExist = false;
            /////////////////////////////////////////////

            if(isExist) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isConnected", true);
                editor.apply();
            }

        });
        Button binscription = findViewById(R.id.inscription_button_connexion);
        binscription.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, InscriptionJobSearcher.class);
            startActivity(myIntent);
        });
    }
}