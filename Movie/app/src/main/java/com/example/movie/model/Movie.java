package com.example.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("vote_average")
    @Expose
    private int voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public Movie(int id, int voteAverage, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        return false;
    }
}
