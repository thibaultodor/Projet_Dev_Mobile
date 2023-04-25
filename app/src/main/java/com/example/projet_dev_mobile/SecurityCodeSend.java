package com.example.projet_dev_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecurityCodeSend extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_code_send);

        Button Submit = findViewById(R.id.SignUpInscriptionJS);
        Submit.setOnClickListener(view -> {
            TextView input = findViewById(R.id.InputSecCodeSend);
            if( !input.getText().toString().equals("") )
            {
                //int inputCode = input.getText().toString().parseInt();

            }
        });
        }
    }
}