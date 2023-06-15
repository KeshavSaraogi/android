package com.example.vaccines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity
        implements ItemClickListener {

    RecyclerView recyclerView;
    VaccineModel[] vaccineList;
    VaccineAdapter vaccineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        vaccineList = new VaccineModel[] {
            new VaccineModel("Hepatitis B Vaccine", R.drawable.hepatitisb),
            new VaccineModel("Tetanus", R.drawable.tetanus),
            new VaccineModel("Pneumococcal Vaccine", R.drawable.pneumococcal),
            new VaccineModel("Rotavirus Vaccine", R.drawable.rotavirus),
            new VaccineModel("Measles Vaccine", R.drawable.measles),
            new VaccineModel("Cholera Vaccine", R.drawable.cholera),
            new VaccineModel("COVID-19 Vaccine", R.drawable.covid),
        };

        //connect the recycler view to the adapter
        vaccineAdapter = new VaccineAdapter(vaccineList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vaccineAdapter);

        //on-click events
        vaccineAdapter.setItemClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this,
                "Vaccine Name: " + vaccineList[position].getName(),
                Toast.LENGTH_SHORT).show();
        }
}