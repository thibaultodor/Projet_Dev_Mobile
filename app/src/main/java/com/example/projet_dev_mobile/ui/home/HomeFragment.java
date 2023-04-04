package com.example.projet_dev_mobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.R;
import com.example.projet_dev_mobile.databinding.FragmentHomeBinding;
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
        cardItemList.add(new CardItem(R.drawable.bear));
        cardItemList.add(new CardItem(R.drawable.bear));
        cardItemList.add(new CardItem(R.drawable.bear));
        cardItemList.add(new CardItem(R.drawable.bear));

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardAdapter = new CardAdapter(cardItemList);
        recyclerView.setAdapter(cardAdapter);

        return view;
    }
}