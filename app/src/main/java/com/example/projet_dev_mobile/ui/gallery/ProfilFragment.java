package com.example.projet_dev_mobile.ui.gallery;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.projet_dev_mobile.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfilFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String json = sharedPreferences.getString("UserJson","");
        boolean isConnected = sharedPreferences.getBoolean("isConnected",false);

        Toast.makeText(getContext(), String.valueOf(isConnected), Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();

        Log.println(Log.DEBUG,"DEBUG",json);

        if(isConnected) {
            try {
                JSONObject obj = new JSONObject(json);
                TextView tname = view.findViewById(R.id.user_name);
                tname.setText("Nom : " + obj.getString("nom"));

                TextView tprenom = view.findViewById(R.id.user_prenom);
                tprenom.setText("Prenom : " + obj.getString("prenom"));

                TextView tnaissance = view.findViewById(R.id.user_datenaissance);
                tnaissance.setText("Date de naissance : " + obj.getString("dateNaissance"));

                TextView tnationalite = view.findViewById(R.id.user_nationalite);
                tnationalite.setText("Nationalité : " + obj.getString("nationalite"));

                TextView ttelephone = view.findViewById(R.id.user_phone);
                ttelephone.setText("Téléphone : " + obj.getString("numTelephone"));

                TextView temail = view.findViewById(R.id.user_email);
                temail.setText("eMail : " + obj.getString("email"));

                TextView tville = view.findViewById(R.id.user_ville);
                tville.setText("Ville : " + obj.getString("ville"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return view;
    }
}