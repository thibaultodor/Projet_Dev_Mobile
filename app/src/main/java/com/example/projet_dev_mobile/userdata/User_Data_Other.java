package com.example.projet_dev_mobile.userdata;
import android.os.Parcel;
import android.os.Parcelable;

public class User_Data_Other implements Parcelable {
    private String nom_entreprise;
    private String nom_service;
    private String nom_sous_service;
    private String num_nationale;
    private String nom_contact_1;
    private String nom_contact_2;
    private String adresse_mail_1;
    private String adresse_mail_2;

    public void set_User_data_part2(String num_tel_1,String num_tel_2,String adresse,String site_web,String linkedin,String facebook) {
        this.num_tel_1 = num_tel_1;
        this.num_tel_2 = num_tel_2;
        this.adresse = adresse;
        this.site_web = site_web;
        this.linkedin = linkedin;
        this.facebook = facebook;
    }

    private String num_tel_1;
    private String num_tel_2;
    private String adresse;
    private String site_web;
    private String linkedin;
    private String facebook;

    public User_Data_Other(String nom_entreprise, String nom_service, String nom_sous_service, String num_nationale, String nom_contact_1, String nom_contact_2, String adresse_mail_1, String adresse_mail_2) {
        this.nom_entreprise = nom_entreprise;
        this.nom_service = nom_service;
        this.nom_sous_service = nom_sous_service;
        this.num_nationale = num_nationale;
        this.nom_contact_1 = nom_contact_1;
        this.nom_contact_2 = nom_contact_2;
        this.adresse_mail_1 = adresse_mail_1;
        this.adresse_mail_2 = adresse_mail_2;
        this.num_tel_1 = "";
        this.num_tel_2 = "";
        this.adresse = "";
        this.site_web = "";
        this.linkedin = "";
        this.facebook = "";
    }

    protected User_Data_Other(Parcel in) {
        nom_entreprise = in.readString();
        nom_service = in.readString();
        nom_sous_service = in.readString();
        num_nationale = in.readString();
        nom_contact_1 = in.readString();
        nom_contact_2 = in.readString();
        adresse_mail_1 = in.readString();
        adresse_mail_2 = in.readString();
        num_tel_1 = in.readString();
        num_tel_2 = in.readString();
        adresse = in.readString();
        site_web = in.readString();
        linkedin = in.readString();
        facebook = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom_entreprise);
        dest.writeString(nom_service);
        dest.writeString(nom_sous_service);
        dest.writeString(num_nationale);
        dest.writeString(nom_contact_1);
        dest.writeString(nom_contact_2);
        dest.writeString(adresse_mail_1);
        dest.writeString(adresse_mail_2);
        dest.writeString(num_tel_1);
        dest.writeString(num_tel_2);
        dest.writeString(adresse);
        dest.writeString(site_web);
        dest.writeString(linkedin);
        dest.writeString(facebook);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User_Data_Other> CREATOR = new Creator<User_Data_Other>() {
        @Override
        public User_Data_Other createFromParcel(Parcel in) {
            return new User_Data_Other(in);
        }

        @Override
        public User_Data_Other[] newArray(int size) {
            return new User_Data_Other[size];
        }
    };
}