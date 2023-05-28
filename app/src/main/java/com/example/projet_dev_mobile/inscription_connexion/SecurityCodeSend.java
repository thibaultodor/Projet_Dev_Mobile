package com.example.projet_dev_mobile.inscription_connexion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.home.Home_Anonyme;
import com.example.projet_dev_mobile.home.Home_Candidat;
import com.example.projet_dev_mobile.home.Home_Employeur;
import com.example.projet_dev_mobile.userdata.User_Data_Candidat;
import com.example.projet_dev_mobile.userdata.User_Data_Other;
import com.google.gson.Gson;

public class SecurityCodeSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_code_send);

        Bundle extras = getIntent().getExtras();
        String choice = extras.getString("choice_account");

        // Create a unique channel ID
        String channelId = "your_channel_id";
        // Create a human-readable channel name
        String channelName = "Your Channel Name";
        // Create an instance of NotificationChannel
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        // Configure the notification channel
        // You can set additional properties like description, sound, vibration, etc.
        // Get the NotificationManager system service
        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        // Create the notification channel
        notificationManager.createNotificationChannel(channel);
        // Create a unique notification ID
        int notificationId = 1;
        // Create an intent to launch when the user taps the notification
        Intent intentnotif = new Intent(this, ConnexionScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentnotif, PendingIntent.FLAG_IMMUTABLE);
        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_favorite_24)  // Set your notification icon
                .setContentTitle("Votre code de sécurité")
                .setContentText("123456")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);  // Dismiss the notification when the user taps it
        // Show the notification
        notificationManager.notify(notificationId, builder.build());

        Button bretour = findViewById(R.id.retourOffre_connexion);
        bretour.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, Home_Anonyme.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(myIntent);
        });

        Button bCheckCode = findViewById(R.id.check_code_sent);
        bCheckCode.setOnClickListener(view -> {
            ///// HERE CHECK USER CHECK SMS /////
            boolean isCheck = true;
            /////////////////////////////////////////////

            if(isCheck) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = getIntent();

                if(choice.equals("Candidat")) {
                    User_Data_Candidat user = intent.getParcelableExtra("user");
                    String id = DataBase.User.getUsers().Add(
                            user.getNom(),
                            user.getPrenom(),
                            user.getNationalite(),
                            user.getDateNaissance(),
                            user.getNumTelephone(),
                            user.getEmail(),
                            user.getVille(),
                            "en",
                            user.getCommentaire(),
                            user.getMdp(),
                            user.getCv());

                    DataBase d = DataBase.getInstance();
                    d.setJsonUsers(getApplicationContext());

                    editor.putInt("type_of_account",1);
                    editor.apply();
                    editor.putInt("idUser", Integer.parseInt(id));
                    editor.apply();
                    Intent myIntent = new Intent(this, Home_Candidat.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myIntent);
                }
                else if (choice.equals("Employeur") || choice.equals("Agence d'intérim") || choice.equals("Gestionnaire")){
                    User_Data_Other user = intent.getParcelableExtra("user");
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    editor.putString("UserJson",json);
                    editor.apply();
                    if(choice.equals("Employeur")) {
                        editor.putInt("type_of_account",2);
                        editor.apply();
                        Intent myIntent = new Intent(this, Home_Employeur.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(myIntent);
                    }
                }
            }
        });
    }
}