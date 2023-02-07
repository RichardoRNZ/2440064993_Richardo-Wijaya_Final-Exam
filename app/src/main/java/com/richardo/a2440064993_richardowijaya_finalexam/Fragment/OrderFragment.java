package com.richardo.a2440064993_richardowijaya_finalexam.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.richardo.a2440064993_richardowijaya_finalexam.Adapter.OrderAdapter;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Ticket;
import com.richardo.a2440064993_richardowijaya_finalexam.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private RecyclerView ticket;
    private OrderAdapter orderAdapter;
    private List<Ticket>tickets = new ArrayList<>();
    private DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment,null);
        ticket = view.findViewById(R.id.Rv_tickets);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ticket");
        getAllOrder();

        return view;
    }
    private void getAllOrder()
    {
        orderAdapter = new OrderAdapter(tickets,getContext());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        tickets.add(dataSnapshot.getValue(Ticket.class));
                    }
                }
                orderAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ticket.setAdapter(orderAdapter);
        ticket.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }
}
