package com.example.creatingmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox firstBox, secondBox;
    Button button;
    RadioButton first, second;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstBox = findViewById(R.id.firstCheckBox);
        secondBox = findViewById(R.id.secondCheckBox);
        button = findViewById(R.id.button);
        first = findViewById(R.id.with);
        second = findViewById(R.id.without);
        spinner = findViewById(R.id.firstSpinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "First Box Is Clicked", Toast.LENGTH_SHORT).show();
                }
                if (secondBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Second Box Is Clicked", Toast.LENGTH_SHORT).show();
                }
                if (first.isChecked()) {
                    Toast.makeText(getApplicationContext(), "First Radio Clicked", Toast.LENGTH_SHORT).show();
                }
                if (second.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Second Radio Button Clicked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting up a spinner to display the data source
        //With Spinner, there are three components:
        // 1. Data Source  2. Adapter  3. Spinner

        String[] courses = {"C", "C++", "Java", "Python", "Kotlin", "JS"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //on-click listening with spinners
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You Selected " + courses[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.first_menu, menu);
        return true;
    }
}
