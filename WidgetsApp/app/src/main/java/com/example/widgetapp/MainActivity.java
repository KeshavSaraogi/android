package com.example.widgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        textView.setBackgroundColor(Color.RED);

        editText = findViewById(R.id.editText);
        String input = editText.getText().toString();

        button = findViewById(R.id.button);

        //handling the on-click listener event
        button.setOnClickListener(new View.OnClickListener() {
            //the action performed when the button is clicked
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You Clicked The Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}