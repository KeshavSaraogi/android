package com.example.creatingmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox firstBox, secondBox;
    Button button, timeButton, dateButton;
    RadioButton first, second;
    Spinner spinner;
    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        dateButton = findViewById(R.id.buttonDate);
        timeButton = findViewById(R.id.timeButton);
        spinner = findViewById(R.id.firstSpinner);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTime = "Time: " + timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute();
                Toast.makeText(getApplicationContext(), "" + currentTime, Toast.LENGTH_LONG).show();

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
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timeFragment= new TimePickerFragment();
                timeFragment.show(getSupportFragmentManager(), "Pick A Time Now: ");
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Pick A Date Now: ");
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
