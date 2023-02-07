package com.richardo.a2440064993_richardowijaya_finalexam.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.richardo.a2440064993_richardowijaya_finalexam.Adapter.MovieAdapter;
import com.richardo.a2440064993_richardowijaya_finalexam.Adapter.OnMovieListener;
import com.richardo.a2440064993_richardowijaya_finalexam.Adapter.SliderPagerAdapter;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Slide;
import com.richardo.a2440064993_richardowijaya_finalexam.MovieDetailActivity;
import com.richardo.a2440064993_richardowijaya_finalexam.R;
import com.richardo.a2440064993_richardowijaya_finalexam.ViewModel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnMovieListener {
    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Slide> slides;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,null);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        recyclerView = view.findViewById(R.id.Rv_movies);
        viewPager = view.findViewById(R.id.slider_pager);
        tabLayout = view.findViewById(R.id.indicator);

        SlidePoster();
        configureRecycleView();
        ObserveAnyChange();
        MovieApi(1);

        return view;
    }
    private void ObserveAnyChange()
    {
        movieListViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies !=null)
                {
                   for(Movie movie:movies)
                   {
                       movieAdapter.setMovieList(movies);
//                       Log.d("tag", "" + movie.getTitle());
                   }

                }
            }
        });
    }
    private void configureRecycleView()
    {
        movieAdapter = new MovieAdapter(this,getContext());
        Log.d("tag",""+getContext());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

    }
    private void MovieApi(int pagenumber)
    {
        movieListViewModel.MovieApi(pagenumber);
    }
    private void SlidePoster()
    {
        slides = new ArrayList<>();
        slides.add(new Slide(R.drawable.poster));
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),slides);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie",movieAdapter.getSelectedMovie(position));
        startActivity(intent);
    }


}
