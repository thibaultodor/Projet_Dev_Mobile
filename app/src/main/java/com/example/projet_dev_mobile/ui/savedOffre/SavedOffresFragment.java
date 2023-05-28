package com.example.projet_dev_mobile.ui.savedOffre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.BDD.Offre;
import com.example.projet_dev_mobile.BDD.SavedCardItem;
import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.ui.CardAdapter;
import com.example.projet_dev_mobile.ui.CardItem;

import java.util.ArrayList;
import java.util.List;

public class SavedOffresFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savedoffres, container, false);

        List<CardItem> cardItemList = new ArrayList<>();

        for (int i = 0; DataBase.Offer.getOffers().get(i) != null; i++)
        {
            String[] data = DataBase.Offer.getOffers().get(i);
            cardItemList.add(new CardItem(R.drawable.bear,data[DataBase.Offer.NOM],data[DataBase.Offer.URL]));
        }

        cardItemList.remove(0);
        cardItemList.remove(0);

        //BDD_Offres b = new BDD_Offres();

        //cardItemList.add(new CardItem(R.drawable.bear, b.getListeOffre().get(1)));
        //cardItemList.add(new CardItem(R.drawable.bear, b.getListeOffre().get(4)));

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardAdapter = new CardAdapter(cardItemList,getContext());
        recyclerView.setAdapter(cardAdapter);

        return view;
    }
}