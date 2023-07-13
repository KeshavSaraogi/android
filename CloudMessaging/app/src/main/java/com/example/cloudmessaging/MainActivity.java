package com.example.cloudmessaging;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button firstButton, secondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelID = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_LOW));
        }

        if (getIntent().getExtras() != null) {
            for (String key: getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "KEY: " + key + " VALUE: " + value);
            }
        }

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance()
                        .subscribeToTopic("weather")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String message = "SUBSCRIBED";
                                if (!task.isSuccessful()) {
                                    message = "FAILED";
                                }
                                Log.d(TAG, message);
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging
                        .getInstance()
                        .getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    Log.v(TAG, "FAILED TO REGISTER TOKEN ", task.getException());
                                    return;
                                }
                                String token = task.getResult();
                                String message = getString(R.string.msg_token_fmt, token);
                                Log.v(TAG, message);
                                Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
