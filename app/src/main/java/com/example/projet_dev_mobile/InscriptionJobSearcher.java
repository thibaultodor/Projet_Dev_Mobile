package com.example.projet_dev_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionJobSearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_job_searcher);
        Button bvalid = findViewById(R.id.inscription_button);
        bvalid.setOnClickListener(view -> {
            boolean allfill = true;
            TextView nom = findViewById(R.id.nom);
            if (nom.getText().toString().equals("")) {nom.setHintTextColor(Color.RED);allfill=false;}
            else {nom.setHintTextColor(Color.BLACK);}
            TextView prenom = findViewById(R.id.prenom);
            if (prenom.getText().toString().equals("")) {prenom.setHintTextColor(Color.RED);allfill=false;}
            else {prenom.setHintTextColor(Color.BLACK);}
            TextView telephone = findViewById(R.id.telephone);
            if (telephone.getText().toString().equals("")) {telephone.setHintTextColor(Color.RED);allfill=false;}
            else {telephone.setHintTextColor(Color.BLACK);}

            allfill = true;
            if(allfill){
                Intent myIntent = new Intent(this, SecurityCodeSend.class);
                //User_Data user = new User_Data()
                //myIntent.putExtra("user",user);
                startActivity(myIntent);
            }
        });
        Button bretour = findViewById(R.id.retourOffre_inscription);
        bretour.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, Home.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        });
    }
}