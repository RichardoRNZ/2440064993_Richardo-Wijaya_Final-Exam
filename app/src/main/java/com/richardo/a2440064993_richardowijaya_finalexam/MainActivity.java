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
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.richardo.a2440064993_richardowijaya_finalexam.Fragment.HomeFragment;
import com.richardo.a2440064993_richardowijaya_finalexam.Fragment.MapFragment;
import com.richardo.a2440064993_richardowijaya_finalexam.Fragment.OrderFragment;
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
    MapFragment mapFragment;
    OrderFragment orderFragment;
    boolean PermissionGranted = false;


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



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home_btn:
                fragmentManager.beginTransaction()
//                        .hide(activeFragment)
//                        .show(homeFragment)
                        .replace(R.id.fragment_container,homeFragment)
                        .addToBackStack(null)
                        .commit();
//                activeFragment = homeFragment;
                return true;

            case R.id.cinema_btn:
               if(checkLocationPermission())
               {
//                   activeFragment = homeFragment;
                   mapFragment = new MapFragment();
                   fragmentManager.beginTransaction()
                           .replace(R.id.fragment_container,mapFragment)
                           .commit();
                   fragmentManager.beginTransaction()
//                           .hide(activeFragment)
//                           .show(mapFragment)
                           .addToBackStack(null)
                           .commit();
//                   activeFragment = mapFragment;
               }
                return true;
            case R.id.order_btn:
            {
//                activeFragment = homeFragment;
                orderFragment = new OrderFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,orderFragment)
//                        .hide(activeFragment)
//                        .show(orderFragment)
                        .addToBackStack(null)
                        .commit();
//                activeFragment = orderFragment;


            }



        }
        return false;
    }
    private void CheckPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                PermissionGranted = true;
                Toast.makeText(MainActivity.this, "Permission Allowed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    public boolean checkGooglePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(MainActivity.this, "Cancelled Dialog", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        return false;
    }
    private boolean checkLocationPermission()
    {
        CheckPermission();
        if(PermissionGranted)
        {
            if(checkGooglePlayServices())
            {
                return true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Google Service Not Available", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return false;
    }

}