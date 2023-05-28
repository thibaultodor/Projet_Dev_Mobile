package com.example.projet_dev_mobile.ui.chat;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, contacts);

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

        return view;
    }
}