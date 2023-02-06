package com.richardo.a2440064993_richardowijaya_finalexam.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
   private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }
    public void MovieApi(int pagenumber)
    {
        movieRepository.movieApi(pagenumber);
    }
}
