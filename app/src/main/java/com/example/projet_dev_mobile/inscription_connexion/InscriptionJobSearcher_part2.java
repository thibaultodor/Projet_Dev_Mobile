package com.example.projet_dev_mobile.inscription_connexion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.home.Home_Candidat;
import com.example.projet_dev_mobile.userdata.User_Data_Candidat;
import com.example.projet_dev_mobile.userdata.User_Data_Other;

public class InscriptionJobSearcher_part2 extends AppCompatActivity {

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
                Intent myIntent = new Intent(this, SecurityCodeSend.class);
                myIntent.putExtra("user", user);
                myIntent.putExtra("choice_account", choice);
                startActivity(myIntent);
            });
            Button bretour = findViewById(R.id.retourOffre_inscription);
            bretour.setOnClickListener(view -> {
                Intent myIntent = new Intent(this, Home_Candidat.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            });
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
                Intent myIntent = new Intent(this, Home_Candidat.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            });
        }
    }
}