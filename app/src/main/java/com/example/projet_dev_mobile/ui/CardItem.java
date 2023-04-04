package com.example.projet_dev_mobile.ui;

public class CardItem {
    private String buttonText;
    private int imageResource;

    public CardItem(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getImageResource() {
        return imageResource;
    }
}