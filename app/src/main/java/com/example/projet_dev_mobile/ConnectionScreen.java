package com.example.projet_dev_mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ConnectionScreen extends AppCompatActivity
{
    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_screen);

        Random rand = new Random();
        code = rand.nextInt( 10000 );


    }
}
