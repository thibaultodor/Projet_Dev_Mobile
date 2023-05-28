package com.example.projet_dev_mobile.ui;

import android.widget.TextView;

import com.example.projet_dev_mobile.BDD.Offre;

public class CardItem {
    private String name;
    private String url;
    private int imageResource;

    public CardItem(int imageResource,String name,String url) {
        this.imageResource = imageResource;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getUrl() {
        return url;
    }
}