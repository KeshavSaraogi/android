package com.example.moviesapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapplication.model.Movie;
import com.example.moviesapplication.model.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public MutableLiveData<ArrayList<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
