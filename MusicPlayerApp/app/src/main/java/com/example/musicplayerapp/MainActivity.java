package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static int once = 0;
    double startTime = 0;
    double finalTime = 0;
    int forwardTime = 10000; //milliseconds
    int backwardTime = 10000; // milliseconds

    TextView timeLeft, songPlaying;
    Button playArrowButton, fastForwardButton, fastRewindButton, pauseButton;
    SeekBar songProgress;

    MediaPlayer mediaPlayer;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeLeft = findViewById(R.id.timeLeft);
        songPlaying = findViewById(R.id.songPlaying);
        playArrowButton = findViewById(R.id.playArrowButton);
        fastForwardButton = findViewById(R.id.fastForwardButton);
        fastRewindButton = findViewById(R.id.fastRewindButton);
        pauseButton = findViewById(R.id.pauseButton);
        songProgress = findViewById(R.id.songProgress);

        mediaPlayer = MediaPlayer.create(this,R.raw.austronaut);
        songProgress.setClickable(false);
    }
}