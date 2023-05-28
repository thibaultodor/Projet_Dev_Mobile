package com.example.projet_dev_mobile.pdfTest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class Pdf extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_layout);
        pdfView=findViewById(R.id.pdfView);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);
        String[] data = DataBase.User.getUsers().get(DataBase.User.ID, String.valueOf(idUser));

        if(data[DataBase.User.CV].isEmpty()){
            Toast.makeText(this,"Aucun CV importé", Toast.LENGTH_SHORT).show();
        }

        File file = new File(getApplicationContext().getFilesDir(),data[DataBase.User.CV]);
        pdfView.fromFile(file).load();
    }
}
