package com.example.worldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private static CustomAdapter customAdapter;
    ArrayList<CountryModel> dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);                               // listview       : adapter view

        dataModel = new ArrayList<>();                                      // data model     : data source
        dataModel.add(new CountryModel("Brazil", "5", R.drawable.brazil));
        dataModel.add(new CountryModel("Germany", "4", R.drawable.germany));
        dataModel.add(new CountryModel("France", "2", R.drawable.france));
        dataModel.add(new CountryModel("Spain", "1", R.drawable.spain));
        dataModel.add(new CountryModel("United Kingdom", "1", R.drawable.uk));
        dataModel.add(new CountryModel("Saudi Arabia", "0", R.drawable.saudiarabia));
        dataModel.add(new CountryModel("USA", "0", R.drawable.usa));

        customAdapter = new CustomAdapter(dataModel, getApplicationContext());  // custom adapter : adapter
        listView.setAdapter(customAdapter);

        //handling click events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                            "Country: " + customAdapter.getItem(position).getCountryName() + "\n" +
                            "World Cup Wins: " + customAdapter.getItem(position).getCupWins(),
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}