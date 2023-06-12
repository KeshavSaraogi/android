package com.example.videoplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    VideoView videoViewURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Local Storage
        videoView = findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://" +
                getPackageName() + "/" + R.raw.mountains);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //URL
        videoViewURL = findViewById(R.id.videoViewURL);

        Uri URI = Uri.parse("https://static.videezy.com/system/resources/previews/000/002/231/original/5226496.mp4");
        videoViewURL.setVideoURI(URI);
        MediaController mediaControllerURL = new MediaController(this);
        mediaControllerURL.setAnchorView(videoViewURL);
        videoViewURL.setMediaController(mediaControllerURL);
        videoViewURL.start();
    }
}
