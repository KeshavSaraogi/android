package com.example.texttransalation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.mlkit.nl.translate.TranslateLanguage;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    String languageCode;
    String fromLanguageCode, toLanguageCode;

    private Spinner toLanguageSpinner, fromLanguageSpinner;
    private EditText sourceTextET;
    private Button translateButton;
    private TextView translatedTV;

    String[] fromLanguages = {
            "FROM", "ENGLISH", "AFRIKAANS", "ARABIC", "BELARUSIAN", "CATALAN", "HINDI", "URDU" };

    String[] toLanguages = {
            "FROM", "ENGLISH", "AFRIKAANS", "ARABIC", "BELARUSIAN", "CATALAN", "HINDI", "URDU" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromLanguageSpinner = findViewById(R.id.fromLanguageSpinner);
        toLanguageSpinner = findViewById(R.id.toLanguageSpinner);
        sourceTextET = findViewById(R.id.sourceTextET);
        translatedTV = findViewById(R.id.translatedTV);
        translateButton = findViewById(R.id.translateButton);

        fromLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode = getLanguageCode(fromLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_item,fromLanguages);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromLanguageSpinner.setAdapter(fromAdapter);

        toLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguageCode = getLanguageCode(toLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter toAdapter = new ArrayAdapter(this,R.layout.spinner_item, toLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toLanguageSpinner.setAdapter(toAdapter);
    }

    private String getLanguageCode(String language) {
        String languageCode = "";

        switch (language) {
            case "ENGLISH":
                languageCode = TranslateLanguage.ENGLISH;
                break;
            case "AFRIKAANS":
                languageCode = TranslateLanguage.AFRIKAANS;
                break;
            case "ARABIC":
                languageCode = TranslateLanguage.ARABIC;
                break;
            case "BELARUSIAN":
                languageCode = TranslateLanguage.BELARUSIAN;
                break;
            case "CATALAN":
                languageCode = TranslateLanguage.CATALAN;
                break;
            case "HINDI":
                languageCode = TranslateLanguage.HINDI;
                break;
            case "URDU":
                languageCode = TranslateLanguage.URDU;
                break;
            default:
                languageCode = "";
        }
        return languageCode;
    }
}