package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        DisplaySavedText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText = editText.getText().toString();
                DisplayAndSaveText(enteredText);
            }
        });
    }

    private void DisplaySavedText() {
        //Retrieve Data From Shared Pref
        SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s = pref.getString("Name"," ");
        textView.setText(s);
    }

    private void DisplayAndSaveText(String text) {
        textView.setText(text);

        // saving data into shared pref
        SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        //writing data to shared pref
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Name", text);
        editor.commit();
    }
}