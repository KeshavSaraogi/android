package com.example.topgames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CardModel> gamesList;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        gamesList = new ArrayList<>();
        gamesList.add(new CardModel("Horizon Chase", R.drawable.horizon));
        gamesList.add(new CardModel("PUBG", R.drawable.pubg));
        gamesList.add(new CardModel("Head Ball 2", R.drawable.headball));
        gamesList.add(new CardModel("Hooked On You", R.drawable.hooked));
        gamesList.add(new CardModel("FIFA", R.drawable.fifa));
        gamesList.add(new CardModel("Fortnite", R.drawable.fortnite));

        cardAdapter = new CardAdapter(this, gamesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cardAdapter);
    }
}