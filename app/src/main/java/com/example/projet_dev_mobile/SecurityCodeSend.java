package com.example.projet_dev_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Toast;

import com.example.projet_dev_mobile.userdata.User_Data;
import com.google.gson.Gson;

public class SecurityCodeSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_code_send);

        Button bCheckCode = findViewById(R.id.check_code_sent);
        bCheckCode.setOnClickListener(view -> {
            ///// HERE CHECK USER CHECK SMS /////
            boolean isCheck = true;
            /////////////////////////////////////////////

            if(isCheck) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isConnected",true);
                editor.apply();

                Intent intent = getIntent();
                User_Data user = intent.getParcelableExtra("user");

                /// PUT DATA IN TABLE HERE ///

                ///

                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putString("UserJson",json);
                editor.apply();

                Intent myIntent = new Intent(this, Home.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            }
        });
    }
}