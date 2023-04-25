package com.example.projet_dev_mobile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardItem> cardItemList;

    public CardAdapter(List<CardItem> cardItemList) {
        this.cardItemList = cardItemList;
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
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
