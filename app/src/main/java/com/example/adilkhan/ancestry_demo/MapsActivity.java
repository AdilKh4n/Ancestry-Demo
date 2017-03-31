package com.example.adilkhan.ancestry_demo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> ax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int count = 0;

        ArrayList<CemeteryDetails> details = new ArrayList<CemeteryDetails>();
        details = (ArrayList<CemeteryDetails>)getIntent().getSerializableExtra("adil");

        Log.d("adil", String.valueOf(details));
        for (int i = 0; i < details.size(); i++)
        {

            if(!details.get(i).getLatitude().equals(""))
            {
                double lati = Double.parseDouble(details.get(i).getLatitude());
                double longx = Double.parseDouble(details.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longx)).title(details.get(i).getCemeteryName()));
               count++;
                Toast.makeText(getApplicationContext(),"Cemeteries found: "+count,Toast.LENGTH_SHORT).show();
            }


        }


    }
}
