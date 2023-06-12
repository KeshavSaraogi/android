package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Inquire extends AppCompatActivity {

    Spinner inquireSpinner;
    Button itemSelect;
    Intent switchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire);

        inquireSpinner = findViewById(R.id.inquireSpinner);
        ArrayAdapter <CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inquireSpinner.setAdapter(arrayAdapter);

        itemSelect = findViewById(R.id.itemSelectButton);
        itemSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = inquireSpinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), item,Toast.LENGTH_SHORT).show();
                switchActivity();
            }
        });
    }

    private void switchActivity() {
        switchActivity = new Intent(getApplicationContext(), ProductDetail.class);
        startActivity(switchActivity);
    }
}