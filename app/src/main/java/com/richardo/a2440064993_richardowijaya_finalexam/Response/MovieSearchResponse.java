package com.richardo.a2440064993_richardowijaya_finalexam.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()

    private int total;

    @SerializedName("results")
    @Expose()
    private List<Movie> movieList;

    public MovieSearchResponse() {
        movieList = new ArrayList<>();
    }

    public int getTotal() {
        return total;
    }

    public List<Movie> getMovieList() {

        return movieList;
    }
}
