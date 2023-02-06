package com.richardo.a2440064993_richardowijaya_finalexam.Request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.richardo.a2440064993_richardowijaya_finalexam.AppExecutor;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Response.MovieSearchResponse;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<Movie>> movies;
    private static MovieApiClient instance;
    private RetrieveMoviesRunnable retrieveMoviesRunnable;



    public static MovieApiClient getInstace() {
        if(instance==null)
        {
            instance = new MovieApiClient();
        }
        return instance;
    }
    private MovieApiClient()
    {
        movies = new MutableLiveData<>();
    }
    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }
    public void MoviesApI(int PageNumber){
        if(retrieveMoviesRunnable!=null)
        {
            retrieveMoviesRunnable= null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(PageNumber);
        final Future myHandler = AppExecutor.getInstance().getmNetworkIO().submit(retrieveMoviesRunnable);
        AppExecutor.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancel retrofit
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS);
    }
    //retrieve data retrofit
    private class RetrieveMoviesRunnable implements Runnable{

        private int PageNumber;

        public RetrieveMoviesRunnable(int pageNumber) {
            PageNumber = pageNumber;
        }

        @Override
        public void run() {

            try {
                Response response = getMovies().execute();
                if(response.code()==200)
                {

                    List<Movie> movieslist = new ArrayList<>(((MovieSearchResponse)response.body()).getMovieList());
                   if(PageNumber==1)
                   {
                       movies.postValue(movieslist);
                   }
                   else {
                       List<Movie>currentMovie = movies.getValue();
                       currentMovie.addAll(movieslist);
                   }

                }
                else {
                    String error = response.errorBody().string();
                    Log.v("tag",""+error);
                    movies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                movies.postValue(null);
            }


        }
        private Call<MovieSearchResponse>getMovies()
        {
            return Service.getMovieApi().searchMovie(CredentialsKey.API_KEY,String.valueOf(PageNumber));

        }
    }

}
