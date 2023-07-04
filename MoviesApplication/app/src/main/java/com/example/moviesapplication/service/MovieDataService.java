package com.example.moviesapplication.service;

import com.example.moviesapplication.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    //BASE URL - https://api.themoviedb.org/3/
    //ENDPOINT - tv/popular?api_key=5363cb14781680ea2ede9e8174ec6a8a
    @GET("tv/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}
