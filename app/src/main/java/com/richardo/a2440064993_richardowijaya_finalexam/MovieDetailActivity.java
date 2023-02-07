package com.richardo.a2440064993_richardowijaya_finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Ticket;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;

import java.util.Random;
import java.util.UUID;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView thumbnail,poster;
    private TextView title,release_date,description;
    private Button buy_button;
    private DatabaseReference databaseReference;
    private Random random = new Random();
    private static final String[] CINEMA = {"CGP A", "CGP B"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        thumbnail = findViewById(R.id.detail_movie_cover);
        poster = findViewById(R.id.detail_movie);
        title = findViewById(R.id.detail_movie_title);
        release_date = findViewById(R.id.movie_date);
        description = findViewById(R.id.detail_movie_desc);
        buy_button = findViewById(R.id.btn_buy);
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ticket");
        getDataFromIntent();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel("Order Notification","Order Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }


    }
    private String randomBookingCode()
    {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("-", "");
    }
    private void backToHomePage()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void getDataFromIntent()
    {
        if(getIntent().hasExtra("movie"))
        {
            Movie movie = getIntent().getParcelableExtra("movie");
           title.setText(movie.getTitle());
           description.setText(movie.getOverview());
           release_date.setText(movie.getRelease_date());
            Glide.with(this)
                    .load(CredentialsKey.IMAGE_URL + movie.getPoster_path())
                    .into(poster);
            Glide.with(this)
                    .load(CredentialsKey.IMAGE_URL + movie.getBackdrop_path())
                    .into(thumbnail);

            buy_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ticket_id = randomBookingCode();
                    String movie_title = movie.getTitle();
                    String movie_image = movie.getPoster_path();
                    int index = random.nextInt(CINEMA.length);
                    String cinema = CINEMA[index];
                    Ticket ticket = new Ticket(ticket_id,movie_title,cinema,movie_image);
                    databaseReference.push().setValue(ticket);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MovieDetailActivity.this,"Order Notification");
                    builder.setContentTitle(movie_title+" Ticket Order")
                            .setContentText("Order "+movie_title+" Ticket Success")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setAutoCancel(true);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MovieDetailActivity.this);
                    notificationManagerCompat.notify(1,builder.build());
                    backToHomePage();

                }
            });

        }

    }

}

