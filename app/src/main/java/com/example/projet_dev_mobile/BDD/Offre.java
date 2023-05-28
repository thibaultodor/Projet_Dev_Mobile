package com.example.projet_dev_mobile.BDD;

public class Offre {
    private String name;

    private String urlOffre;

    public Offre(String name, String urlOffre) {
        this.name = name;
        this.urlOffre = urlOffre;
    }

    public String getName() {
        return name;
    }

    public String getUrlOffre() {
        return urlOffre;
    }
}
