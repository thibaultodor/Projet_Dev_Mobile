package com.example.projet_dev_mobile.ui.chat;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ContactListFragment extends Fragment {

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // Initialize the contact list
        List<String> contacts = new ArrayList<>();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);

        List<Integer> listeIdDuplicate = DataBase.Conversation.getConversations().getContactIds(idUser);
        Set<Integer> s = new LinkedHashSet<>(listeIdDuplicate);
        List<Integer> listeId = new ArrayList<>(s);
        for (Integer i:listeId) {
            contacts.add(DataBase.User.getUsers().get(DataBase.User.ID,i.toString())[DataBase.User.PRENOM]);
        }
        // Create the ArrayAdapter for the contact list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.simple_list_item_custom, contacts);

        // Get the ListView and set the adapter
        ListView contactListView = view.findViewById(R.id.contactListView);
        contactListView.setAdapter(adapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), ChatActivity.class);
                intent.putExtra("iduser1", idUser);
                intent.putExtra("iduser2", listeId.get(position));
                startActivity(intent);
            }
        });

        Button newMessage = view.findViewById(R.id.newMessage);
        newMessage.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Nouveau message");
            final EditText prenom = new EditText(getActivity());
            final EditText nom = new EditText(getActivity());
            final EditText message = new EditText(getActivity());

            prenom.setHint("Prenom");
            nom.setHint("Nom");
            message.setHint("Message");

            message.setHeight(200);

            LinearLayout ll=new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(prenom);
            ll.addView(nom);
            ll.addView(message);
            alertDialog.setView(ll);
            alertDialog.setPositiveButton("Envoyer",
                    (dialog, id) -> {
                        DataBase d = DataBase.getInstance();
                        int isExistId = d.isExistNewMessage(getContext(),prenom.getText().toString(),nom.getText().toString());

                        if(isExistId!=-1){
                            if(message.getText().toString().equals("")){
                                Toast.makeText(getContext(), "Message vide", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                DataBase.Conversation.getConversations().Add(idUser,isExistId,message.getText().toString(),idUser);
                                Toast.makeText(getContext(), "Message envoyé", Toast.LENGTH_SHORT).show();
                                d.setJSonConversation(getContext());
                                dialog.cancel();
                                List<String> contacts2 = new ArrayList<>();
                                List<Integer> listeIdDuplicate2 = DataBase.Conversation.getConversations().getContactIds(idUser);
                                Set<Integer> s2 = new LinkedHashSet<>(listeIdDuplicate2);
                                List<Integer> listeId2 = new ArrayList<>(s2);
                                for (Integer i:listeId2) {
                                    contacts2.add(DataBase.User.getUsers().get(DataBase.User.ID,i.toString())[DataBase.User.PRENOM]);
                                }
                                // Create the ArrayAdapter for the contact list
                                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), R.layout.simple_list_item_custom, contacts2);

                                // Get the ListView and set the adapter
                                ListView contactListView2 = view.findViewById(R.id.contactListView);
                                contactListView2.setAdapter(adapter2);
                            }
                        }else{
                            Toast.makeText(getContext(), "Utilisateur inconnu", Toast.LENGTH_SHORT).show();
                        }
                    });
            alertDialog.setNegativeButton("Annuler",
                    (dialog, id) -> {
                        Toast.makeText(getContext(), "Message non envoyé", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    });

            AlertDialog alert11 = alertDialog.create();
            alert11.show();
        });

        return view;
    }
}