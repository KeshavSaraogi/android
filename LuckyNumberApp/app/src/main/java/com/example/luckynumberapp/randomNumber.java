package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class randomNumber extends AppCompatActivity {

    TextView luckyNumberText;
    Button shareButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        intent = getIntent();
        luckyNumberText = findViewById(R.id.luckyNumber);
        shareButton = findViewById(R.id.shareButton);

        String username = intent.getStringExtra("name");

        int randomNumber = generateRandomNumber();
        luckyNumberText.setText("" + randomNumber);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(username, randomNumber);
            }
        });
    }
    public int generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        return randomNumber;
    }

    public void shareData(String name, int number) {

        String num = String.valueOf(number);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, name + " Got Lucky Today!");
        intent.putExtra(Intent.EXTRA_TEXT,"His Lucky Number Is " + number);

        startActivity(Intent.createChooser(intent, "Choose A Platform"));
    }
}