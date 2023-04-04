package com.example.projet_dev_mobile;

import static android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ApplicationsListFragment extends Fragment
{
    public ApplicationsListFragment() {
        super(R.layout.application_list);
    }

    private ListView list;
    private SimpleDateFormat sdf;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date firstDate, secondDate, sentDate;
        try {
            firstDate = sdf.parse("05/06/2023");
            secondDate = sdf.parse("20/06/2023");
            sentDate = sdf.parse("20/05/2023");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        List<Application> test = new ArrayList<>();
        for (int i=0; i<10; ++i)
        {
            test.add(new Application(
                    "Metier"+i,
                    "Employeur"+i,
                    "Ville"+i,
                    "https://example.com",
                    firstDate,
                    secondDate,
                    sentDate,
                    "aaaaaaaaaaaaaaa",
                    i%2==0
                    )
            );
        }

        list = view.findViewById(R.id.applications);
        list.setAdapter(new ApplicationArrayAdapter(getContext(), R.layout.application_list_item, test));
    }

    private class Application
    {
        public String metier;
        public String employeur;
        public String lieu;
        public String url;
        public Date start;
        public Date end;
        public Date sent;
        public String answer;
        public boolean confirmed;
        Application(String metier, String employeur, String lieu, String url, Date start, Date end, Date sent, String answer, boolean confirmed)
        {
            this.metier = metier;
            this.employeur = employeur;
            this.lieu = lieu;
            this.url = url;
            this.start = start;
            this.end = end;
            this.sent = sent;
            this.answer = answer;
            this.confirmed = confirmed;
        }
    }

    private class ApplicationArrayAdapter extends ArrayAdapter<Application>
    {
        public ApplicationArrayAdapter(@NonNull Context context, int resource, @NonNull List<Application> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.application_list_item, parent, false);

            Application item = getItem(position);

            TextView metierView = convertView.findViewById(R.id.application_item_metier);
            TextView employeurView = convertView.findViewById(R.id.application_item_employeur);
            TextView lieuView = convertView.findViewById(R.id.application_item_lieu);
            TextView urlView = convertView.findViewById(R.id.application_item_url);
            TextView periodView = convertView.findViewById(R.id.application_item_period);
            TextView sentView = convertView.findViewById(R.id.application_item_sentDate);
            TextView confirmedView = convertView.findViewById(R.id.application_item_confirmed);

            metierView.setText(item.metier);
            employeurView.setText(item.employeur);
            lieuView.setText(String.format(" - %s", item.lieu));
            urlView.setText(item.url);

            periodView.setText(String.format("Du %s au %s", sdf.format(item.start), sdf.format(item.end)));
            sentView.setText(String.format("Envoyée le : %s", sdf.format(item.sent)));

            if (item.confirmed)
            {
                confirmedView.setText("Validée");
                confirmedView.setTextColor(Color.GREEN);
            }
            else
            {
                confirmedView.setText("");
            }

            return convertView;
        }
    }
}
