package com.example.projet_dev_mobile.BDD;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataBase
{
    private static DataBase DB;

    private DataBase(){}

    public static DataBase getInstance()
    {
        if( DB == null )
            DB = new DataBase();
        
        return DB;
    }

    static public class User
    {
        private static User Users;
        private static int id = 0;

        private List<String[]> data;

        public static final int ID = 0;
        public static final int NOM = 1;
        public static final int PRENOM = 2;
        public static final int NATIONALITE = 3;
        public static final int DATE_NAISSANCE = 4;
        public static final int NUM_TEL = 5;
        public static final int MAIL = 6;
        public static final int VILLE = 7;
        public static final int COMMENTAIRE = 8;
        public static final int MOT_DE_PASSE = 9;
        public static final int CV = 10;
        public static final int LANGUAGE = 11;
        public static final int ALL_COMP = 12;

        private User()
        {
            this.data = new ArrayList<String[]>();
        }

        public static User getUsers()
        {
            if( Users == null )
                Users = new User();

            return Users;
        }

        public String Add( String nom, String prenom, String nat, String date_n, String num_tel, String mail, String ville, String language, String comm, String mdp, String cv )
        {
            String[] s = new String[ALL_COMP];

            s[ID] = Integer.toString(id++);
            s[NOM] = nom;
            s[PRENOM] = prenom;
            s[NATIONALITE] = nat;
            s[DATE_NAISSANCE] = date_n;
            s[NUM_TEL] = num_tel;
            s[MAIL] = mail;
            s[VILLE] = ville;
            s[LANGUAGE] = language;
            s[COMMENTAIRE] = comm;
            s[MOT_DE_PASSE] = mdp;
            s[CV] = cv;

            Users.data.add( s );

            return s[ID];
        }

        public boolean Delete( int index )
        {
            if( index < 0 || index >= data.size() )
                return false;

            data.remove(index);
            return true;
        }

        public boolean Delete( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return false;

            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[comp].equals(value) )
                {
                    data.remove(i);
                    return true;
                }

            return false;
        }

        public String[] get( int index )
        {
            if( index < 0 || index >= data.size() )
                return null;

            return data.get(index);
        }

        public String[] get( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return null;

            for( String[] E : data )
                if( E[comp].equals(value) )
                    return E;

            return null;
        }

        public void setLanguage( int id, String new_language )
        {
            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[ID].equals(Integer.toString(id)) )
                    data.get(i)[LANGUAGE] = new_language;
        }

        public void setMotDePasse( int id, String new_mdp )
        {
            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[ID].equals(Integer.toString(id)) )
                    data.get(i)[MOT_DE_PASSE] = new_mdp;
        }

        public void setCV( int id, String cv )
        {
            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[ID].equals(Integer.toString(id)) )
                    data.get(i)[CV] = cv;
        }

        private String toJSON()
        {
            if( data.size() == 0 )
                return "{\n\"users\":\n[\n]\n}\n";

            String s = new String("{\n\"users\":\n[\n");
            int i = 0;

            for( ; i < data.size()-1; i++ )
            {
                String[] E = data.get(i);
                s += "{ \"id\" : \"" + E[ID] + "\",\n"
                        + " \"nom\" : \"" + E[NOM] + "\",\n"
                        + " \"prenom\" : \"" + E[PRENOM] + "\",\n"
                        + " \"nationalite\" : \"" + E[NATIONALITE] + "\",\n"
                        + " \"date_naissance\" : \"" + E[DATE_NAISSANCE] + "\",\n"
                        + " \"num_tel\" : \"" + E[NUM_TEL] + "\",\n"
                        + " \"mail\" : \"" + E[MAIL] + "\",\n"
                        + " \"ville\" : \"" + E[VILLE] + "\",\n"
                        + " \"langue\" : \"" + E[LANGUAGE] + "\",\n"
                        + " \"commentaire\" : \"" + E[COMMENTAIRE] + "\",\n"
                        + " \"cv\" : \"" + E[CV] + "\",\n"
                        + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n},";
            }

            String[] E = data.get(i);
            s += "{ \"id\" : \"" + E[ID] + "\",\n"
                    + " \"nom\" : \"" + E[NOM] + "\",\n"
                    + " \"prenom\" : \"" + E[PRENOM] + "\",\n"
                    + " \"nationalite\" : \"" + E[NATIONALITE] + "\",\n"
                    + " \"date_naissance\" : \"" + E[DATE_NAISSANCE] + "\",\n"
                    + " \"num_tel\" : \"" + E[NUM_TEL] + "\",\n"
                    + " \"mail\" : \"" + E[MAIL] + "\",\n"
                    + " \"ville\" : \"" + E[VILLE] + "\",\n"
                    + " \"langue\" : \"" + E[LANGUAGE] + "\",\n"
                    + " \"commentaire\" : \"" + E[COMMENTAIRE] + "\",\n"
                    + " \"cv\" : \"" + E[CV] + "\",\n"
                    + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n}\n]\n}\n";

            return s;
        }
    }


    public void setJsonUsersTest(Context context){
        User.getUsers().Add("LIRZIN", "Léo", "Fr", "22-04-00", "063890054", "leolirzin@gmail.Com", "Versailles","en","", "broken&phone","");
        User.getUsers().Add("PITOT DE LA BEAUJARDIERE", "Julien", "US", "22-10-99", "0123456789", "juju@hotmail.com", "Paris","en","Bottom professionel", "sparadrap","");
        User.getUsers().Add("ODOR", "Thibault", "Fr", "22-11-98", "9876543210", "titi@gmail.com", "Montpellier", "en","","4569","");
        // System.out.print(DataBase.User.getUsers().get(0)[0]);
        // DataBase.User.getUsers().Delete(DataBase.User.PRENOM, "Léo");
        // System.out.print(DataBase.User.getUsers().get(0)[0]);
        System.out.print(User.getUsers().toJSON());
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Users",User.getUsers().toJSON());
        editor.apply();
    }

    public void setJsonUsers(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Users",User.getUsers().toJSON());
        editor.apply();
    }

    public void reBaseBDDUser(Context context){
        User.Users = null;
        User.id = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("Users","");
        if(json.isEmpty()){
            Log.println(Log.ERROR,"DataBase","NO Users in JSON !!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        else{
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray listUsers = obj.getJSONArray("users");
                for (int i = 0; i < listUsers.length(); i++) {
                    JSONObject user = listUsers.getJSONObject(i);
                    User.getUsers().Add(
                            user.getString("nom"),
                            user.getString("prenom"),
                            user.getString("nationalite"),
                            user.getString("date_naissance"),
                            user.getString("num_tel"),
                            user.getString("mail"),
                            user.getString("ville"),
                            user.getString("langue"),
                            user.getString("commentaire"),
                            user.getString("mot_de_passe"),
                            user.getString("cv"));
                }
                Log.println(Log.ERROR,"DataBase",User.getUsers().toJSON());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isExist(Context context, String mailnumtel,String motDePasse){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("Users","");
        boolean isExist = false;
        if(json.isEmpty()){
            Log.println(Log.ERROR,"DataBase","NO Users in JSON !!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        else{
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray listUsers = obj.getJSONArray("users");
                for (int i = 0; i < listUsers.length(); i++) {
                    JSONObject user = listUsers.getJSONObject(i);
                    String mail = user.getString("mail");
                    String numtel = user.getString("num_tel");
                    String mdp = user.getString("mot_de_passe");
                    if(mdp.equals(motDePasse)){
                        if(mail.equals(mailnumtel)){
                            isExist = true;
                        }
                        if(numtel.equals(mailnumtel)){
                            isExist = true;
                        }
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return isExist;
    }

    public int isExistNewMessage(Context context, String prenom,String nom){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("Users","");
        int isExistid = -1;
        if(json.isEmpty()){
            Log.println(Log.ERROR,"DataBase","NO Users in JSON !!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        else{
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray listUsers = obj.getJSONArray("users");
                for (int i = 0; i < listUsers.length(); i++) {
                    JSONObject user = listUsers.getJSONObject(i);

                    String id = user.getString("id");
                    String pren = user.getString("prenom");
                    String no = user.getString("nom");
                    if(pren.equalsIgnoreCase(prenom)){
                        if(no.equalsIgnoreCase(nom)){
                            isExistid = Integer.parseInt(id);
                        }
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return isExistid;
    }

    static public class Employer
{
    private static Employer Employers;
    private static int id = 0;

    private List<String[]> data;

    public static final int ID = 0;
    public static final int NOM_ENTREPRISE = 1;
    public static final int NOM_DEPARTEMENT = 2;
    public static final int NOM_SOUS_DEPARTEMENT = 3;
    public static final int NUM_NATIONALE = 4;
    public static final int NOM_CONTACT_1 = 5;
    public static final int NOM_CONTACT_2 = 6;
    public static final int MAIL_1 = 7;
    public static final int MAIL_2 = 8;
    public static final int NUM_TEL_1 = 9;
    public static final int NUM_TEL_2 = 10;
    public static final int ADRESSE = 11;
    public static final int SITE_WEB = 12;
    public static final int LINKEDIN = 13;
    public static final int FACEBOOK = 14;
    public static final int MOT_DE_PASSE = 15;
    public static final int ALL_COMP = 16;

    private Employer()
    {
        this.data = new ArrayList<String[]>();
    }

    public static Employer getEmployers()
    {
        if( Employers == null )
            Employers = new Employer();

        return Employers;
    }

    public void Add( String nom_entreprise, String nom_departement, String nom_sous_departement, String num_nationale, String nom_contact_1,
                     String nom_contact_2, String mail_1, String mail_2, String num_tel_1, String num_tel_2, String adresse, String site_web,
                     String linkedin, String facebook, String mot_de_passe )
    {
        String[] s = new String[ALL_COMP];

        s[ID] = Integer.toString(id++);
        s[NOM_ENTREPRISE] = nom_entreprise;
        s[NOM_DEPARTEMENT] = nom_departement;
        s[NOM_SOUS_DEPARTEMENT] = nom_sous_departement;
        s[NUM_NATIONALE] = num_nationale;
        s[NOM_CONTACT_1] = nom_contact_1;
        s[NOM_CONTACT_2] = nom_contact_2;
        s[MAIL_1] = mail_1;
        s[MAIL_2] = mail_2;
        s[NUM_TEL_1] = num_tel_1;
        s[NUM_TEL_2] = num_tel_2;
        s[ADRESSE] = adresse;
        s[SITE_WEB] = site_web;
        s[LINKEDIN] = linkedin;
        s[FACEBOOK] = facebook;
        s[MOT_DE_PASSE] = mot_de_passe;

        Employers.data.add( s );
    }

    public boolean Delete( int index )
    {
        if( index < 0 || index >= data.size() )
            return false;

        data.remove(index);
        return true;
    }

    public boolean Delete( int comp, String value )
    {
        if( comp < ID || comp >= ALL_COMP )
            return false;

        for( int i = 0; i < data.size(); i++ )
            if( data.get(i)[comp].equals(value) )
            {
                data.remove(i);
                return true;
            }

        return false;
    }

    public String[] get( int index )
    {
        if( index < 0 || index >= data.size() )
            return null;

        return data.get(index);
    }

    public String[] get( int comp, String value )
    {
        if( comp < ID || comp >= ALL_COMP )
            return null;

        for( String[] E : data )
            if( E[comp].equals(value) )
                return E;

        return null;
    }


    private String toJSON()
    {
        if( data.size() == 0 )
            return "{\n\"employers\":\n[\n]\n}\n";

        String s = new String("{\n\"employers\":\n[\n");
        int i = 0;

        for( ; i < data.size()-1; i++ )
        {
            String[] E = data.get(i);
            s += "{ \"id\" : \"" + E[ID] + "\",\n"
                    + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                    + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                    + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                    + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                    + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                    + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                    + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                    + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                    + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                    + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                    + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                    + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                    + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                    + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                    + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n},";
        }

        String[] E = data.get(i);
        s += "{ \"id\" : \"" + E[ID] + "\",\n"
                + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n}\n]\n}\n";

        return s;
    }
}

    static public class Agency
    {
        private static Agency Agencies;
        private static int id = 0;

        private List<String[]> data;

        public static final int ID = 0;
        public static final int NOM_ENTREPRISE = 1;
        public static final int NOM_DEPARTEMENT = 2;
        public static final int NOM_SOUS_DEPARTEMENT = 3;
        public static final int NUM_NATIONALE = 4;
        public static final int NOM_CONTACT_1 = 5;
        public static final int NOM_CONTACT_2 = 6;
        public static final int MAIL_1 = 7;
        public static final int MAIL_2 = 8;
        public static final int NUM_TEL_1 = 9;
        public static final int NUM_TEL_2 = 10;
        public static final int ADRESSE = 11;
        public static final int SITE_WEB = 12;
        public static final int LINKEDIN = 13;
        public static final int FACEBOOK = 14;
        public static final int MOT_DE_PASSE = 15;
        public static final int ALL_COMP = 16;

        private Agency()
        {
            this.data = new ArrayList<String[]>();
        }

        public static Agency getAgencies()
        {
            if( Agencies == null )
                Agencies = new Agency();

            return Agencies;
        }

        public void Add( String nom_entreprise, String nom_departement, String nom_sous_departement, String num_nationale, String nom_contact_1,
                         String nom_contact_2, String mail_1, String mail_2, String num_tel_1, String num_tel_2, String adresse, String site_web,
                         String linkedin, String facebook, String mot_de_passe )
        {
            String[] s = new String[ALL_COMP];

            s[ID] = Integer.toString(id++);
            s[NOM_ENTREPRISE] = nom_entreprise;
            s[NOM_DEPARTEMENT] = nom_departement;
            s[NOM_SOUS_DEPARTEMENT] = nom_sous_departement;
            s[NUM_NATIONALE] = num_nationale;
            s[NOM_CONTACT_1] = nom_contact_1;
            s[NOM_CONTACT_2] = nom_contact_2;
            s[MAIL_1] = mail_1;
            s[MAIL_2] = mail_2;
            s[NUM_TEL_1] = num_tel_1;
            s[NUM_TEL_2] = num_tel_2;
            s[ADRESSE] = adresse;
            s[SITE_WEB] = site_web;
            s[LINKEDIN] = linkedin;
            s[FACEBOOK] = facebook;
            s[MOT_DE_PASSE] = mot_de_passe;

            Agencies.data.add( s );
        }

        public boolean Delete( int index )
        {
            if( index < 0 || index >= data.size() )
                return false;

            data.remove(index);
            return true;
        }

        public boolean Delete( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return false;

            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[comp].equals(value) )
                {
                    data.remove(i);
                    return true;
                }

            return false;
        }

        public String[] get( int index )
        {
            if( index < 0 || index >= data.size() )
                return null;

            return data.get(index);
        }

        public String[] get( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return null;

            for( String[] E : data )
                if( E[comp].equals(value) )
                    return E;

            return null;
        }

        private String toJSON()
        {
            if( data.size() == 0 )
                return "{\n\"agencies\":\n[\n]\n}\n";

            String s = new String("{\n\"agencies\":\n[\n");
            int i = 0;

            for( ; i < data.size()-1; i++ )
            {
                String[] E = data.get(i);
                s += "{ \"id\" : \"" + E[ID] + "\",\n"
                        + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                        + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                        + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                        + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                        + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                        + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                        + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                        + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                        + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                        + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                        + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                        + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                        + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                        + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                        + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n},";
            }

            String[] E = data.get(i);
            s += "{ \"id\" : \"" + E[ID] + "\",\n"
                    + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                    + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                    + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                    + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                    + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                    + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                    + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                    + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                    + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                    + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                    + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                    + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                    + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                    + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                    + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n}\n]\n}\n";

            return s;
        }
    }

    static public class Administrator
    {
        private static Administrator Administrators;
        private static int id = 0;

        private List<String[]> data;

        public static final int ID = 0;
        public static final int NOM_ENTREPRISE = 1;
        public static final int NOM_DEPARTEMENT = 2;
        public static final int NOM_SOUS_DEPARTEMENT = 3;
        public static final int NUM_NATIONALE = 4;
        public static final int NOM_CONTACT_1 = 5;
        public static final int NOM_CONTACT_2 = 6;
        public static final int MAIL_1 = 7;
        public static final int MAIL_2 = 8;
        public static final int NUM_TEL_1 = 9;
        public static final int NUM_TEL_2 = 10;
        public static final int ADRESSE = 11;
        public static final int SITE_WEB = 12;
        public static final int LINKEDIN = 13;
        public static final int FACEBOOK = 14;
        public static final int MOT_DE_PASSE = 15;
        public static final int ALL_COMP = 16;

        private Administrator()
        {
            this.data = new ArrayList<String[]>();
        }

        public static Administrator getAdministrators()
        {
            if( Administrators == null )
                Administrators = new Administrator();

            return Administrators;
        }

        public void Add( String nom_entreprise, String nom_departement, String nom_sous_departement, String num_nationale, String nom_contact_1,
                         String nom_contact_2, String mail_1, String mail_2, String num_tel_1, String num_tel_2, String adresse, String site_web,
                         String linkedin, String facebook, String mot_de_passe )
        {
            String[] s = new String[ALL_COMP];

            s[ID] = Integer.toString(id++);
            s[NOM_ENTREPRISE] = nom_entreprise;
            s[NOM_DEPARTEMENT] = nom_departement;
            s[NOM_SOUS_DEPARTEMENT] = nom_sous_departement;
            s[NUM_NATIONALE] = num_nationale;
            s[NOM_CONTACT_1] = nom_contact_1;
            s[NOM_CONTACT_2] = nom_contact_2;
            s[MAIL_1] = mail_1;
            s[MAIL_2] = mail_2;
            s[NUM_TEL_1] = num_tel_1;
            s[NUM_TEL_2] = num_tel_2;
            s[ADRESSE] = adresse;
            s[SITE_WEB] = site_web;
            s[LINKEDIN] = linkedin;
            s[FACEBOOK] = facebook;
            s[MOT_DE_PASSE] = mot_de_passe;

            Administrators.data.add( s );
        }

        public boolean Delete( int index )
        {
            if( index < 0 || index >= data.size() )
                return false;

            data.remove(index);
            return true;
        }

        public boolean Delete( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return false;

            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[comp].equals(value) )
                {
                    data.remove(i);
                    return true;
                }

            return false;
        }

        public String[] get( int index )
        {
            if( index < 0 || index >= data.size() )
                return null;

            return data.get(index);
        }

        public String[] get( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return null;

            for( String[] E : data )
                if( E[comp].equals(value) )
                    return E;

            return null;
        }

        private String toJSON()
        {
            if( data.size() == 0 )
                return "{\n\"administrators\":\n[\n]\n}\n";

            String s = new String("{\n\"administrators\":\n[\n");
            int i = 0;

            for( ; i < data.size()-1; i++ )
            {
                String[] E = data.get(i);
                s += "{ \"id\" : \"" + E[ID] + "\",\n"
                        + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                        + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                        + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                        + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                        + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                        + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                        + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                        + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                        + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                        + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                        + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                        + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                        + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                        + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                        + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n},";
            }

            String[] E = data.get(i);
            s += "{ \"id\" : \"" + E[ID] + "\",\n"
                    + " \"nom_entreprise\" : \"" + E[NOM_ENTREPRISE] + "\",\n"
                    + " \"nom_departement\" : \"" + E[NOM_DEPARTEMENT] + "\",\n"
                    + " \"nom_sous_departement\" : \"" + E[NOM_SOUS_DEPARTEMENT] + "\",\n"
                    + " \"num_nationale\" : \"" + E[NUM_NATIONALE] + "\",\n"
                    + " \"nom_contact_1\" : \"" + E[NOM_CONTACT_1] + "\",\n"
                    + " \"nom_contact_2\" : \"" + E[NOM_CONTACT_2] + "\",\n"
                    + " \"mail_1\" : \"" + E[MAIL_1] + "\",\n"
                    + " \"mail_2\" : \"" + E[MAIL_2] + "\",\n"
                    + " \"num_tel_1\" : \"" + E[NUM_TEL_1] + "\",\n"
                    + " \"num_tel_2\" : \"" + E[NUM_TEL_2] + "\",\n"
                    + " \"adresse\" : \"" + E[ADRESSE] + "\",\n"
                    + " \"site_web\" : \"" + E[SITE_WEB] + "\",\n"
                    + " \"linkedin\" : \"" + E[LINKEDIN] + "\",\n"
                    + " \"facebook\" : \"" + E[FACEBOOK] + "\",\n"
                    + " \"mot_de_passe\" : \"" + E[MOT_DE_PASSE] + "\"\n}\n]\n}\n";

            return s;
        }
    }

    static public class Conversation
    {
        private static Conversation Conversations;
        private static int id = 0;

        private List<Integer> IdConv;
        private List<Integer> IdUser1;
        private List<Integer> IdUser2;
        private List<List<String>> Messages;
        private List<List<Integer>> Speakers;

        private Conversation()
        {
            this.IdConv = new ArrayList<Integer>();
            this.IdUser1 = new ArrayList<Integer>();
            this.IdUser2 = new ArrayList<Integer>();
            this.Messages = new ArrayList<List<String>>();
            this.Speakers =  new ArrayList<List<Integer>>();
        }

        public static Conversation getConversations()
        {
            if( Conversations == null )
                Conversations = new Conversation();

            return Conversations;
        }

        public boolean Add( int IDConv, String message, int speaker )
        {
            for( int i = 0; i < IdConv.size(); i++ )
                if( IdConv.get(i) == IDConv )
                {
                    Messages.get(i).add(message);
                    Speakers.get(i).add(speaker);
                    return true;
                }

            return false;
        }

        public void Add( int IDUser1, int IDUser2, String message, int speaker )
        {
            for( int i = 0; i < IdConv.size(); i++ )
                if( ( IdUser1.get(i) == IDUser1 && IdUser2.get(i) == IDUser2 )
                        || ( IdUser1.get(i) == IDUser2 && IdUser2.get(i) == IDUser1 ) )
                {
                    Messages.get(i).add(message);
                    Speakers.get(i).add(speaker);
                }

            IdConv.add(id++);
            IdUser1.add(IDUser1);
            IdUser2.add(IDUser2);

            List<String> NewMessages = new ArrayList<String>();
            NewMessages.add(message);
            Messages.add( NewMessages );

            List<Integer> NewSpeakers = new ArrayList<Integer>();
            NewSpeakers.add(speaker);
            Speakers.add(NewSpeakers);
        }

        public List<Integer> getContactIds( int IdUser )
        {
            List<Integer> Contacts = new ArrayList<Integer>();

            for( int i = 0; i < IdConv.size(); i++ )
                if( IdUser == IdUser1.get(i) )
                    Contacts.add(IdUser2.get(i));
                else if( IdUser == IdUser2.get(i) )
                    Contacts.add(IdUser1.get(i));

            return Contacts;
        }

        public int GetConversation( int SenderId, int ReceiverId,List<String> messages, List<Boolean> isSender )
        {
            for( int i = 0; i < IdConv.size(); i++ )
                if( ( IdUser1.get(i) == SenderId && IdUser2.get(i) == ReceiverId )
                        || ( IdUser2.get(i) == SenderId && IdUser1.get(i) == ReceiverId ) )
                {
                    for( int j = 0; j < Messages.get(i).size(); j++ )
                    {
                        messages.add( Messages.get(i).get(j) );
                        isSender.add( Speakers.get(i).get(j) == SenderId );
                    }
                    return i;
                }

            return -1;
        }

        private String toJSON()
        {
            String s = new String("{\n\"conversations\":\n[\n");
            int i = 0;

            for( ; i < IdConv.size()-1; i++ )
            {
                s += "{ \"id\" : \"" + IdConv.get(i) + "\",\n"
                        + " \"iDUser1\" : \"" + IdUser1.get(i) + "\",\n"
                        + " \"iDUser2\" : \"" + IdUser2.get(i) + "\",\n"
                        + " \"messages\" : [\n";

                int j = 0;
                for(; j < Messages.get(i).size()-1; j++ )
                    s += "{\n"
                            + "\"text\" : \"" + Messages.get(i).get(j) + "\",\n"
                            + "\"id\" : \"" + Speakers.get(i).get(j) + "\"\n},\n";

                s += "{\n"
                        + "\"text\" : \"" + Messages.get(i).get(j) + "\",\n"
                        + "\"id\" : \"" + Speakers.get(i).get(j) + "\"\n}\n";


                s +=  "\n]\n},";
            }

            s += "{ \"id\" : \"" + IdConv.get(i) + "\",\n"
                    + " \"iDUser1\" : \"" + IdUser1.get(i) + "\",\n"
                    + " \"iDUser2\" : \"" + IdUser2.get(i) + "\",\n"
                    + " \"messages\" : [\n";

            int j = 0;
            for(; j < Messages.get(i).size()-1; j++ )
                s += "{\n"
                        + "\"text\" : \"" + Messages.get(i).get(j) + "\",\n"
                        + "\"id\" : \"" + Speakers.get(i).get(j) + "\"\n},\n";

            s += "{\n"
                    + "\"text\" : \"" + Messages.get(i).get(j) + "\",\n"
                    + "\"id\" : \"" + Speakers.get(i).get(j) + "\"\n}\n";


            s += "]\n}\n]\n}\n";

            return s;
        }
    }

    public void setJsonConversationTest(Context context){
        DataBase.Conversation.getConversations().Add(1, 2, "Hello",  1);
        DataBase.Conversation.getConversations().Add(0, "Hi, how are you ?", 2);
        DataBase.Conversation.getConversations().Add(0, "Good thank you", 1);
        DataBase.Conversation.getConversations().Add( 0, 1, "Lol", 0);
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Conversations",DataBase.Conversation.getConversations().toJSON());
        editor.apply();
    }

    public void setJSonConversation(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Conversations","");
        editor.apply();
        editor.putString("Conversations",DataBase.Conversation.getConversations().toJSON());
        editor.apply();
    }

    public void reBaseBDDConversation(Context context){
        Conversation.Conversations = null;
        Conversation.id = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("Conversations","");
        if(json.isEmpty()){
            Log.println(Log.ERROR,"DataBase","NO Conversations in JSON !!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        else{
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray listconversations = obj.getJSONArray("conversations");
                for (int i = 0; i < listconversations.length(); i++) {
                    JSONObject conversation = listconversations.getJSONObject(i);
                    int idUser1 = Integer.parseInt(conversation.getString("iDUser1"));
                    int iDUser2 = Integer.parseInt(conversation.getString("iDUser2"));
                    JSONArray listeMessages = conversation.getJSONArray("messages");
                    JSONObject message0 = listeMessages.getJSONObject(0);
                    DataBase.Conversation.getConversations().Add(idUser1, iDUser2, message0.getString("text"), Integer.parseInt(message0.getString("id")));
                    for (int j = 1; j <listeMessages.length() ; j++) {
                        JSONObject message = listeMessages.getJSONObject(j);
                        DataBase.Conversation.getConversations().Add(i, message.getString("text"), Integer.parseInt(message.getString("id")));
                    }
                }
                Log.println(Log.ERROR,"DataBase",Conversation.getConversations().toJSON());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isEmpty(){
        return (User.getUsers().data.isEmpty() && Conversation.getConversations().Messages.size() == 0);
    }

    static public class Offer
    {
        private static Offer Offers;
        private static int id = 0;

        private List<String[]> data;

        public static final int ID = 0;
        public static final int NOM = 1;
        public static final int URL = 2;
        public static final int ALL_COMP = 3;

        private Offer()
        {
            this.data = new ArrayList<String[]>();
        }

        public static Offer getOffers()
        {
            if( Offers == null )
                Offers = new Offer();

            return Offers;
        }

        public void Add( String nom, String url )
        {
            String[] s = new String[ALL_COMP];

            s[ID] = Integer.toString(id++);
            s[NOM] = nom;
            s[URL] = url;

            Offers.data.add( s );
        }

        public boolean Delete( int index )
        {
            if( index < 0 || index >= data.size() )
                return false;

            data.remove(index);
            return true;
        }

        public boolean Delete( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return false;

            for( int i = 0; i < data.size(); i++ )
                if( data.get(i)[comp].equals(value) )
                {
                    data.remove(i);
                    return true;
                }

            return false;
        }

        public String[] get( int index )
        {
            if( index < 0 || index >= data.size() )
                return null;

            return data.get(index);
        }

        public String[] get( int comp, String value )
        {
            if( comp < ID || comp >= ALL_COMP )
                return null;

            for( String[] E : data )
                if( E[comp].equals(value) )
                    return E;

            return null;
        }


        private String toJSON()
        {
            if( data.size() == 0 )
                return "{\n\"offers\":\n[\n]\n}\n";

            String s = new String("{\n\"offers\":\n[\n");
            int i = 0;

            for( ; i < data.size()-1; i++ )
            {
                String[] E = data.get(i);
                s += "{ \"id\" : \"" + E[ID] + "\",\n"
                        + " \"nom\" : \"" + E[NOM] + "\",\n"
                        + " \"url\" : \"" + E[URL] + "\"\n},";
            }

            String[] E = data.get(i);
            s += "{ \"id\" : \"" + E[ID] + "\",\n"
                    + " \"nom\" : \"" + E[NOM] + "\",\n"
                    + " \"url\" : \"" + E[URL] + "\"\n}\n]\n}\n";

            return s;
        }
    }



    public void setJSonOffer(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Offres","");
        editor.apply();
        editor.putString("Offres",DataBase.Offer.getOffers().toJSON());
        editor.apply();
    }

    public void reBaseBDDOffres(Context context){
        Offer.Offers = null;
        Offer.id = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json = sharedPreferences.getString("Offres","");
        if(json.isEmpty()){
            Log.println(Log.ERROR,"DataBase","NO Offres in JSON !!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        else{
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray listeOffre = obj.getJSONArray("offers");
                for (int i = 0; i < listeOffre.length(); i++) {
                    JSONObject Offre = listeOffre.getJSONObject(i);
                    Offer.getOffers().Add(Offre.getString("nom"),Offre.getString("url"));
                }
                Log.println(Log.ERROR,"DataBase",Offer.getOffers().toJSON());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setJsonOfferTest(Context context){
        Offer.getOffers().Add("L'Atelier Banette :\n Snackeur/Snackeuse","https://candidat.pole-emploi.fr/offres/recherche/detail/155FXJH");
        Offer.getOffers().Add("EUROTEL  :\n Valet / Femme de chambre","https://candidat.pole-emploi.fr/offres/recherche/detail/155FZDD");
        Offer.getOffers().Add("WELLJOB  :\n Ouvrier Pépiniériste","https://candidat.pole-emploi.fr/offres/recherche/detail/155FTSN");
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Offres",DataBase.Offer.getOffers().toJSON());
        editor.apply();
    }
}