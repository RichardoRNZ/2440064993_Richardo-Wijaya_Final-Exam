package com.richardo.a2440064993_richardowijaya_finalexam.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.richardo.a2440064993_richardowijaya_finalexam.Fragment.HomeFragment;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.R;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movieList;
    private OnMovieListener onMovieListener;
    private Context context;

    public MovieAdapter(OnMovieListener onMovieListener, Context context) {
        this.onMovieListener = onMovieListener;
        this.context = context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).MovieTitle.setText(movieList.get(position).getTitle());

        Glide.with(holder.itemView.getContext())
                .load(CredentialsKey.IMAGE_URL +movieList.get(position).getPoster_path())
                .into(((MovieViewHolder)holder).MoviePicture);

    }

    @Override
    public int getItemCount() {

      if(movieList!=null)
      {
          return movieList.size();
      }
      return 0;

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
    public Movie getSelectedMovie(int position)
    {
        if(movieList!=null)
        {
            if (movieList.size()>0)
            {
                return movieList.get(position);
            }
        }
        return null;
    }
}
