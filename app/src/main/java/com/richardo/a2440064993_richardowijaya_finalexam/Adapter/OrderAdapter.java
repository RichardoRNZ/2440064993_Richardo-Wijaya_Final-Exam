package com.richardo.a2440064993_richardowijaya_finalexam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Ticket;
import com.richardo.a2440064993_richardowijaya_finalexam.R;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Ticket> tickets;
    private Context context;

    public OrderAdapter(List<Ticket> tickets, Context context) {
        this.tickets = tickets;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder)holder).id.setText(tickets.get(position).getMovieTitle());
        ((OrderViewHolder)holder).title.setText(tickets.get(position).getID());
        ((OrderViewHolder)holder).cinema.setText(tickets.get(position).getCinemaName());
        Glide.with(holder.itemView.getContext())
                .load(CredentialsKey.IMAGE_URL+tickets.get(position).getMoviePoster())
                .into(((OrderViewHolder)holder).poster);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
