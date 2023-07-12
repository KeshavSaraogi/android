package com.example.texttransalation;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

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
            "TO", "ENGLISH", "AFRIKAANS", "ARABIC", "BELARUSIAN", "CATALAN", "HINDI", "URDU" };

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

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translatedTV.setText("");

                if (sourceTextET.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "PLEASE ENTER TEXT",
                            Toast.LENGTH_SHORT).show();
                } else if (fromLanguageCode.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "PLEASE SELECT SOURCE LANGUAGE",
                            Toast.LENGTH_SHORT).show();
                } else if(toLanguageCode.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "PLEASE SELECT TARGET LANGUAGE",
                            Toast.LENGTH_SHORT).show();
                } else {
                    translateText(fromLanguageCode, toLanguageCode, sourceTextET.getText().toString());
                }
            }
        });
    }

    private void translateText(String fromLanguageCode, String toLanguageCode, String source) {
        translatedTV.setText("DOWNLOADING LANGUAGE MODEL");

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(fromLanguageCode).build();

        Translator translator = Translation.getClient(options);
        DownloadConditions conditions = new DownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translatedTV.setText("TRANSLATING ...");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translatedTV.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "FAILED TO TRANSLATE TEXT", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "FAILED TO DOWNLOAD LANGUAGE", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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