package com.example.projet_dev_mobile.BDD;

import com.example.projet_dev_mobile.ui.CardItem;

import java.util.ArrayList;

public class SavedCardItem {

    private ArrayList<CardItem> listSavedCard;

    public SavedCardItem(){
        listSavedCard = new ArrayList<>();
    }

    public ArrayList<CardItem> getListSavedCard() {
        return listSavedCard;
    }
}
