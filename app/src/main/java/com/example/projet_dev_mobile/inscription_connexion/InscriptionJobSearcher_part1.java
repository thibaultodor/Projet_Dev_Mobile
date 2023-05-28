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

public class InscriptionJobSearcher_part1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String choice = extras.getString("choice_account");

        if(choice.equals("Candidat")) {
            setContentView(R.layout.inscription_job_searcher_candidat_part1);

            Button bvalid = findViewById(R.id.suivant_button);
            bvalid.setOnClickListener(view -> {
                boolean allfill = true;
                TextView nom = findViewById(R.id.nom);
                if (nom.getText().toString().equals("")) {
                    nom.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    nom.setHintTextColor(Color.BLACK);
                }
                TextView prenom = findViewById(R.id.prenom);
                if (prenom.getText().toString().equals("")) {
                    prenom.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    prenom.setHintTextColor(Color.BLACK);
                }
                TextView nationalite = findViewById(R.id.nationalite);
                if (nationalite.getText().toString().equals("")) {
                    nationalite.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    nationalite.setHintTextColor(Color.BLACK);
                }
                TextView datenaissance = findViewById(R.id.datenaissance);
                if (datenaissance.getText().toString().equals("")) {
                    datenaissance.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    datenaissance.setHintTextColor(Color.BLACK);
                }
                TextView telephone = findViewById(R.id.telephone);
                if (telephone.getText().toString().equals("")) {
                    telephone.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    telephone.setHintTextColor(Color.BLACK);
                }
                TextView email = findViewById(R.id.email);
                if (email.getText().toString().equals("")) {
                    email.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    email.setHintTextColor(Color.BLACK);
                }
                TextView ville = findViewById(R.id.ville);
                if (ville.getText().toString().equals("")) {
                    ville.setHintTextColor(Color.RED);
                    allfill = false;
                } else {
                    ville.setHintTextColor(Color.BLACK);
                }
                if (allfill) {
                    Intent myIntent = new Intent(this, InscriptionJobSearcher_part2.class);
                    User_Data_Candidat user = new User_Data_Candidat(
                            nom.getText().toString(),
                            prenom.getText().toString(),
                            nationalite.getText().toString(),
                            datenaissance.getText().toString(),
                            telephone.getText().toString(),
                            email.getText().toString(),
                            ville.getText().toString(),
                            "123456","","");
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
        else if (choice.equals("Employeur") || choice.equals("Agence d'intérim") || choice.equals("Gestionnaire")){
            setContentView(R.layout.inscription_job_searcher_other_part1);

            Button bvalid = findViewById(R.id.suivant_button);
            bvalid.setOnClickListener(view -> {
                        boolean allfill = true;
                        TextView nomentreprise = findViewById(R.id.nom_entreprise);
                        if (nomentreprise.getText().toString().equals("")) {
                            nomentreprise.setHintTextColor(Color.RED);
                            allfill = false;
                        } else {
                            nomentreprise.setHintTextColor(Color.BLACK);
                        }
                        TextView nom_service = findViewById(R.id.nom_service);
                        TextView nom_sous_service = findViewById(R.id.nom_sous_service);
                        TextView num_nationale_entite = findViewById(R.id.num_nationale);
                        if (num_nationale_entite.getText().toString().equals("")) {
                            num_nationale_entite.setHintTextColor(Color.RED);
                            allfill = false;
                        } else {
                            num_nationale_entite.setHintTextColor(Color.BLACK);
                        }
                        TextView nom_contact_1 = findViewById(R.id.nom_contact_1);
                        TextView nom_contact_2 = findViewById(R.id.facebook);
                        TextView adresse_mail_1 = findViewById(R.id.adresse_mail_1);
                        if (adresse_mail_1.getText().toString().equals("")) {
                            adresse_mail_1.setHintTextColor(Color.RED);
                            allfill = false;
                        } else {
                            adresse_mail_1.setHintTextColor(Color.BLACK);
                        }
                        TextView adresse_mail_2 = findViewById(R.id.adresse_mail_2);
                        if (allfill) {
                            Intent myIntent = new Intent(this, InscriptionJobSearcher_part2.class);
                            User_Data_Other user = new User_Data_Other(
                                    nomentreprise.getText().toString(),
                                    nom_service.getText().toString(),
                                    nom_sous_service.getText().toString(),
                                    num_nationale_entite.getText().toString(),
                                    nom_contact_1.getText().toString(),
                                    nom_contact_2.getText().toString(),
                                    adresse_mail_1.getText().toString(),
                                    adresse_mail_2.getText().toString());
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