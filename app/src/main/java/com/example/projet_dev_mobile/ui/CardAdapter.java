package com.example.projet_dev_mobile.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.inscription_connexion.ConnexionScreen;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardItem> cardItemList;
    private Context context;

    public CardAdapter(List<CardItem> cardItemList,Context c) {
        this.cardItemList = cardItemList;
        this.context = c;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem cardItem = cardItemList.get(position);
        holder.image.setImageResource(cardItem.getImageResource());
        holder.image.setClipToOutline(true);
        holder.name.setText(cardItem.getName());

        holder.lienversannonce.setOnClickListener(v -> {
            /*
            Intent myIntent = new Intent( holder.lienversannonce.getContext(), ConnexionScreen.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            holder.lienversannonce.getContext().startActivity(myIntent);
            */
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cardItem.getUrl()));
            holder.lienversannonce.getContext().startActivity(intent);
        });

        holder.partager.setOnClickListener(v -> {
            /*
            Intent myIntent = new Intent( holder.lienversannonce.getContext(), ConnexionScreen.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            holder.lienversannonce.getContext().startActivity(myIntent);
            */
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "J'ai utilisé l'application TeLiOd pour trouver cette Offre : \n"+cardItem.getUrl()+"\n Vous aussi téléchargez l'application TeLiOd");
            sendIntent.setType("text/plain");
            holder.partager.getContext().startActivity(Intent.createChooser(sendIntent, "Share This App"));
        });

        holder.traduire.setOnClickListener(v -> {
            String url =cardItem.getUrl();
            // Target language for translation
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
            int idUser = sharedPreferences.getInt("idUser",-1);
            String[] data = DataBase.User.getUsers().get(DataBase.User.ID, String.valueOf(idUser));

            String targetLanguage = data[DataBase.User.LANGUAGE]; // Example: French
            // Build the translated URL
            String translatedUrl = "https://translate.google.com/translate?hl=" + targetLanguage + "&sl=auto&tl=" + targetLanguage + "&u=" + url;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(translatedUrl));
            holder.traduire.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button lienversannonce;
        public Button candidater;
        public Button traduire;
        public Button enregistrer;
        public Button partager;
        public ImageView image;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            lienversannonce = itemView.findViewById(R.id.lienversannonce);
            candidater = itemView.findViewById(R.id.candidater);
            traduire = itemView.findViewById(R.id.traduire);
            enregistrer = itemView.findViewById(R.id.enregistrer);
            partager = itemView.findViewById(R.id.partager);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name_offre);
        }
    }
}
