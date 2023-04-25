package com.example.projet_dev_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InscriptionJobSearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_job_searcher);
        ArrayList<String> info = new ArrayList<>();

        Button SignUp = findViewById(R.id.SignUpInscriptionJS);
        SignUp.setOnClickListener(view -> {
            boolean allfill = true;
            info.clear();

            TextView nom = findViewById(R.id.InputSecCodeSend);
            if( nom.getText().toString().equals("") ) { nom.setHintTextColor(Color.RED); allfill = false; }
            else { nom.setHintTextColor(Color.BLACK); info.add( nom.getText().toString()); }

            TextView prenom = findViewById(R.id.PrenomIncriptionJS);
            if( prenom.getText().toString().equals("") ) { prenom.setHintTextColor(Color.RED); allfill = false; }
            else { prenom.setHintTextColor(Color.BLACK); info.add( prenom.getText().toString()); }

            TextView nationalite = findViewById(R.id.NationaliteIncriptionJS);
            if( nationalite.getText().toString().equals("") ) { nationalite.setHintTextColor(Color.RED); allfill = false; }
            else { nationalite.setHintTextColor(Color.BLACK); info.add( nationalite.getText().toString()); }

            TextView dateNaissance = findViewById(R.id.DateNaissanceIncriptionJS);
            if( dateNaissance.getText().toString().equals("") ) { dateNaissance.setHintTextColor(Color.RED); allfill = false; }
            else { dateNaissance.setHintTextColor(Color.BLACK); info.add( dateNaissance.getText().toString()); }

            TextView telephone = findViewById(R.id.NumetoTelephoneIncriptionJS);
            if( telephone.getText().toString().equals("") ) { telephone.setHintTextColor(Color.RED); allfill = false; }
            else { telephone.setHintTextColor(Color.BLACK); info.add( telephone.getText().toString()); }

            TextView email = findViewById(R.id.EmailIncriptionJS);
            if( email.getText().toString().equals("") ) { email.setHintTextColor(Color.RED); allfill = false; }
            else { email.setHintTextColor(Color.BLACK); info.add( email.getText().toString()); }

            TextView ville = findViewById(R.id.VilleIncriptionJS);
            if( ville.getText().toString().equals("") ) { ville.setHintTextColor(Color.RED); allfill = false; }
            else { ville.setHintTextColor(Color.BLACK); info.add( ville.getText().toString()); }

            TextView commentaires = findViewById(R.id.CommentairesInscriptionJS);
            if( commentaires.getText().toString().equals("") ) { commentaires.setHintTextColor(Color.RED); allfill = false; }
            else { commentaires.setHintTextColor(Color.BLACK); info.add( commentaires.getText().toString()); }

            if(allfill){
                Bundle NewUserData = new Bundle();
                NewUserData.putString("nom",nom.getText().toString());
                NewUserData.putString("prenom",prenom.getText().toString());
                NewUserData.putString("nationalite",nationalite.getText().toString());
                NewUserData.putString("dateNaissance",dateNaissance.getText().toString());
                NewUserData.putString("telephone",telephone.getText().toString());
                NewUserData.putString("email",email.getText().toString());
                NewUserData.putString("ville",ville.getText().toString());
                NewUserData.putString("commentaires",commentaires.getText().toString());


                Intent Intent = new Intent(this, SecurityCodeSend.class);
                Intent.putExtra( "NewUserData", NewUserData );
                startActivity(Intent);
            }
        });

        Button CV = findViewById(R.id.ImporterCVInscriptionJS);
        CV.setOnClickListener(view -> {
            // Go fetch PDF
        });

        Button Back = findViewById(R.id.ReturnSecCodeSend);
        CV.setOnClickListener(view -> {
            finish();
        });
    }
}