package com.example.projet_dev_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        });
        Button binscription = findViewById(R.id.inscription_button_connexion);
        binscription.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, InscriptionJobSearcher.class);
            startActivity(myIntent);
        });
    }
}