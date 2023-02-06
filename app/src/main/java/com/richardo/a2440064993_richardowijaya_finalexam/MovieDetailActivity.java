package com.richardo.a2440064993_richardowijaya_finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView thumbnail,poster;
    private TextView title,release_date,description;
    private Button buy_button;


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
        getDataFromIntent();


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

        }
    }
}