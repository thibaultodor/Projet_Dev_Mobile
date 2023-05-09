package com.example.projet_dev_mobile.userdata;
import android.os.Parcel;
import android.os.Parcelable;

public class User_Data_Candidat implements Parcelable {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String nationalite;
    private String numTelephone;
    private String email;
    private String ville;

    public User_Data_Candidat(String nom, String prenom, String dateNaissance, String nationalite, String numTelephone, String email, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.numTelephone = numTelephone;
        this.email = email;
        this.ville = ville;
    }

    protected User_Data_Candidat(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        dateNaissance = in.readString();
        nationalite = in.readString();
        numTelephone = in.readString();
        email = in.readString();
        ville = in.readString();
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
}