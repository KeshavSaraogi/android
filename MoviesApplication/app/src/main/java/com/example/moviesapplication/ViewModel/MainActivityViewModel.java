package com.example.moviesapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapplication.Model.Movie;
import com.example.moviesapplication.Model.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public MutableLiveData<ArrayList<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
