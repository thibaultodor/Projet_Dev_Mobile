package com.example.projet_dev_mobile.userdata;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User_Data_Candidat implements Parcelable {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String nationalite;
    private String numTelephone;
    private String email;
    private String ville;
    private String mdp;
    private String cv;
    private String commentaire;

    public User_Data_Candidat(String nom, String prenom, String dateNaissance, String nationalite, String numTelephone, String email, String ville, String mdp, String cv, String commentaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.numTelephone = numTelephone;
        this.email = email;
        this.ville = ville;
        this.mdp = mdp;
        this.cv = cv;
        this.commentaire = commentaire;
    }

    protected User_Data_Candidat(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        dateNaissance = in.readString();
        nationalite = in.readString();
        numTelephone = in.readString();
        email = in.readString();
        ville = in.readString();
        mdp = in.readString();
        cv = in.readString();
        commentaire = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(dateNaissance);
        dest.writeString(nationalite);
        dest.writeString(numTelephone);
        dest.writeString(email);
        dest.writeString(ville);
        dest.writeString(mdp);
        dest.writeString(cv);
        dest.writeString(commentaire);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User_Data_Candidat> CREATOR = new Creator<User_Data_Candidat>() {
        @Override
        public User_Data_Candidat createFromParcel(Parcel in) {
            return new User_Data_Candidat(in);
        }

        @Override
        public User_Data_Candidat[] newArray(int size) {
            return new User_Data_Candidat[size];
        }
    };

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getEmail() {
        return email;
    }

    public String getVille() {
        return ville;
    }

    public String getMdp() {
        return mdp;
    }

    public String getCv() {
        return cv;
    }

    public String getCommentaire() {
        return commentaire;
    }
}