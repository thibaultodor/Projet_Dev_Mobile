package com.example.projet_dev_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.userdata.User_Data;

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
            TextView nationalite = findViewById(R.id.nationalite);
            if (nationalite.getText().toString().equals("")) {nationalite.setHintTextColor(Color.RED);allfill=false;}
            else {nationalite.setHintTextColor(Color.BLACK);}
            TextView datenaissance = findViewById(R.id.datenaissance);
            if (datenaissance.getText().toString().equals("")) {datenaissance.setHintTextColor(Color.RED);allfill=false;}
            else {datenaissance.setHintTextColor(Color.BLACK);}
            TextView telephone = findViewById(R.id.telephone);
            if (telephone.getText().toString().equals("")) {telephone.setHintTextColor(Color.RED);allfill=false;}
            else {telephone.setHintTextColor(Color.BLACK);}
            TextView email = findViewById(R.id.email);
            if (email.getText().toString().equals("")) {email.setHintTextColor(Color.RED);allfill=false;}
            else {email.setHintTextColor(Color.BLACK);}
            TextView ville = findViewById(R.id.ville);
            if (ville.getText().toString().equals("")) {ville.setHintTextColor(Color.RED);allfill=false;}
            else {ville.setHintTextColor(Color.BLACK);}
            allfill=true;
            if(allfill){
                Intent myIntent = new Intent(this, SecurityCodeSend.class);
                User_Data user = new User_Data( nom.getText().toString(),
                                                prenom.getText().toString(),
                                                nationalite.getText().toString(),
                                                datenaissance.getText().toString(),
                                                telephone.getText().toString(),
                                                email.getText().toString(),
                                                ville.getText().toString());
                myIntent.putExtra("user",user);
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