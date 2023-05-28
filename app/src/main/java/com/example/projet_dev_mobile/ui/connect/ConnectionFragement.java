package com.example.projet_dev_mobile.ui.connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.inscription_connexion.ConnexionScreen;

public class ConnectionFragement extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_connect, container, false);
        Intent myIntent = new Intent(this.getContext(), ConnexionScreen.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
        return null;
    }
}