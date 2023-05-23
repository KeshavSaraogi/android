package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView welcomeText, resultText;
    Button convertButton;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        resultText = findViewById(R.id.resultText);
        inputText = findViewById(R.id.inputText);
        convertButton = findViewById(R.id.button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double kilograms = Double.parseDouble(inputText.getText().toString());
                double result = convertKiloToPounds(kilograms);
                resultText.setText("" + result + "LB");
            }
        });
    }

    public double convertKiloToPounds(double kilos){
        double result = kilos * 220.5;
        result = Math.round(result);
        result = result / 100;
        return result;
    }
}