package com.example.projet_dev_mobile.userdata;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User_Data implements Parcelable {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String nationalite;
    private int numTelephone;
    private String email;
    private String ville;

    public User_Data(String nom, String prenom, Date dateNaissance, String nationalite, int numTelephone, String email, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.numTelephone = numTelephone;
        this.email = email;
        this.ville = ville;
    }

    protected User_Data(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        nationalite = in.readString();
        numTelephone = in.readInt();
        email = in.readString();
        ville = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(nationalite);
        dest.writeInt(numTelephone);
        dest.writeString(email);
        dest.writeString(ville);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User_Data> CREATOR = new Creator<User_Data>() {
        @Override
        public User_Data createFromParcel(Parcel in) {
            return new User_Data(in);
        }

        @Override
        public User_Data[] newArray(int size) {
            return new User_Data[size];
        }
    };
}