package com.richardo.a2440064993_richardowijaya_finalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.richardo.a2440064993_richardowijaya_finalexam.Fragment.HomeFragment;
import com.richardo.a2440064993_richardowijaya_finalexam.Model.Movie;
import com.richardo.a2440064993_richardowijaya_finalexam.Request.Service;
import com.richardo.a2440064993_richardowijaya_finalexam.Response.MovieSearchResponse;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.MovieApi;
import com.richardo.a2440064993_richardowijaya_finalexam.ViewModel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    Fragment activeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("tag",""+getApplicationContext());
        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container,homeFragment)
                .commit();
        activeFragment = homeFragment;


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home_btn:
                fragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(homeFragment)
                        .addToBackStack("")
                        .commit();
                activeFragment = homeFragment;
                return true;


        }
        return false;
    }
}