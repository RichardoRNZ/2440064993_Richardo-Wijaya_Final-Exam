package com.richardo.a2440064993_richardowijaya_finalexam.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.richardo.a2440064993_richardowijaya_finalexam.R;

public class MapFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;
    public boolean PermissionGranted = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps_ragment,null);

        ViewLocationService();

        return view;

    }
    private void ViewLocationService() {
        supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);

    }
    private void ShowCGPAlpha()
    {

           LatLng CGP_Alpha = new LatLng(-6.193924061113853,106.78813220277623);
           MarkerOptions markerOptions = new MarkerOptions();
           markerOptions.title("CGP Alpha");
           markerOptions.position(CGP_Alpha);
           googleMap.addMarker(markerOptions);
           CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(CGP_Alpha,13);
            googleMap.animateCamera(cameraUpdate);
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    Toast.makeText(getContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(),20);
                    googleMap.moveCamera(cameraUpdate);
                    return false;
                }
            });







    }
    private void ShowCGPBeta()
    {
        LatLng CGP_Beta = new LatLng(-6.20175020412279, 106.78223868546155);
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.title("CGP Beta");
        markerOptions1.position(CGP_Beta);
        googleMap.addMarker(markerOptions1);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Toast.makeText(getContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(),20);
                googleMap.moveCamera(cameraUpdate);
                return false;
            }
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        ShowCGPAlpha();
        ShowCGPBeta();
    }


}
