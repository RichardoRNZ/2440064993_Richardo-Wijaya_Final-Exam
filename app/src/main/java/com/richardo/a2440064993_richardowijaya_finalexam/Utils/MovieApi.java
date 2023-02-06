package com.richardo.a2440064993_richardowijaya_finalexam.Utils;

import com.richardo.a2440064993_richardowijaya_finalexam.Response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {


    @GET("3/movie/now_playing")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("page") String page
    );
}
