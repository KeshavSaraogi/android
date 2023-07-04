package com.example.moviesapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import com.example.moviesapplication.model.Movie;
import com.example.moviesapplication.R;
import com.example.moviesapplication.databinding.ActivityMovieBinding;


public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        activityMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);

        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }
}
