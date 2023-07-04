package com.example.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("movies")
    @Expose
    private List<Movie> movies;
    @SerializedName("total_result")
    @Expose
    private int totalResult;
    @SerializedName("total_page")
    @Expose
    private int totalPages;

    public MovieResponse(int page, List<Movie> movies, int totalResult, int totalPages) {
        this.page = page;
        this.movies = movies;
        this.totalResult = totalResult;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
