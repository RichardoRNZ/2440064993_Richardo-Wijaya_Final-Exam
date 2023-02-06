package com.richardo.a2440064993_richardowijaya_finalexam.Response;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;

public class MovieResponse {
    @SerializedName("result")
    @Expose
    private Movie movie;

    public Movie getMovie()
    {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
