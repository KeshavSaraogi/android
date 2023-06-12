package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;

public class Order extends AppCompatActivity {

    Spinner orderSpinner;
    Spinner weightOptionsSpinner;
    Button orderConfirm;
    Connection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderSpinner = findViewById(R.id.orderSpinner);
        ArrayAdapter<CharSequence> itemsOrderSpinner = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        itemsOrderSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(itemsOrderSpinner);

        weightOptionsSpinner = findViewById(R.id.weightSpinner);
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this, R.array.weight, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightOptionsSpinner.setAdapter(weightAdapter);

        orderConfirm = findViewById(R.id.orderConfirmButton);
        orderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = orderSpinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
