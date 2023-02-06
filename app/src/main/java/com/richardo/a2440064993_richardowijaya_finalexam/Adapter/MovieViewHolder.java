package com.richardo.a2440064993_richardowijaya_finalexam.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richardo.a2440064993_richardowijaya_finalexam.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView MovieTitle;
    ImageView MoviePicture;
    OnMovieListener onMovieListener;
    public MovieViewHolder(@NonNull View itemView,OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        MovieTitle = itemView.findViewById(R.id.item_movie_title);
        MoviePicture = itemView.findViewById(R.id.item_movie_img);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     if(onMovieListener!=null)
     {
         onMovieListener.onMovieClick(getAdapterPosition());

     }

    }
}
