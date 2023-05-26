package com.example.creatingmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox firstBox, secondBox;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstBox = findViewById(R.id.firstCheckBox);
        secondBox = findViewById(R.id.secondCheckBox);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "First Box Is Clicked", Toast.LENGTH_SHORT).show();
                }
                if (secondBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Second Box Is Clicked", Toast.LENGTH_SHORT).show();
                }
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
