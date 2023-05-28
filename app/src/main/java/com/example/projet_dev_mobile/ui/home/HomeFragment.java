package com.example.projet_dev_mobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.BDD.Offre;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.ui.CardAdapter;
import com.example.projet_dev_mobile.ui.CardItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        List<CardItem> cardItemList = new ArrayList<>();

        /*
        BDD_Offres b = new BDD_Offres();
        for (Offre o:b.getListeOffre()) {
            cardItemList.add(new CardItem(R.drawable.bear,o));
        }
        */

        for (int i = 0; DataBase.Offer.getOffers().get(i) != null; i++)
        {
            String[] data = DataBase.Offer.getOffers().get(i);
            cardItemList.add(new CardItem(R.drawable.bear,data[DataBase.Offer.NOM],data[DataBase.Offer.URL]));
        }

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardAdapter = new CardAdapter(cardItemList,getContext());
        recyclerView.setAdapter(cardAdapter);

        return view;
    }
}