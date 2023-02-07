package com.richardo.a2440064993_richardowijaya_finalexam.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richardo.a2440064993_richardowijaya_finalexam.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{
    TextView title,id,cinema;
    ImageView poster;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.ticket_movie_title);
        id = itemView.findViewById(R.id.ticket_id);
        cinema = itemView.findViewById(R.id.ticket_cinema);
        poster = itemView.findViewById(R.id.ticket_movie_img);

    }
}
