package com.richardo.a2440064993_richardowijaya_finalexam.Repository;

import androidx.lifecycle.MutableLiveData;

import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private MovieApiClient movieApiClient;
    private static MovieRepository instace;
    public static MovieRepository getInstance()
    {
        if(instace==null)
        {
            instace = new MovieRepository();
        }
        return instace;
    }
    private MovieRepository()
    {
        movieApiClient = MovieApiClient.getInstace();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieApiClient.getMovies();
    }
    public void movieApi(int pagenumber)
    {
        movieApiClient.MoviesApI(pagenumber);
    }
}
