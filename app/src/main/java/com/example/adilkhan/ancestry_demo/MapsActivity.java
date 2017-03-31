package com.example.adilkhan.ancestry_demo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> ax;
    int count = 0;
    double lati = 0.0;
    double longx = 0.0;
    String cemname=null;
    String cemcountry=null;
    String cemstate=null;
    String cemcounty=null;
    String cemcity= null;

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
        mMap.getUiSettings().setZoomControlsEnabled(true);


        ArrayList<CemeteryDetails> details = new ArrayList<CemeteryDetails>();
        details = (ArrayList<CemeteryDetails>)getIntent().getSerializableExtra("adil");

        Log.d("adil", String.valueOf(details));
        for (int i = 0; i < details.size(); i++)
        {

            if(!details.get(i).getLatitude().equals(""))
            {
                lati = Double.parseDouble(details.get(i).getLatitude());
                longx = Double.parseDouble(details.get(i).getLongitude());
                cemname = details.get(i).getCemeteryName();
                cemcountry = details.get(i).getCountryName();
                cemstate = details.get(i).getStateName();
                cemcounty = details.get(i).getCountyName();
                cemcity = details.get(i).getCityName();

                mMap.addMarker(new MarkerOptions().
                        position(new LatLng(lati, longx)).
                        title(details.get(i).getCemeteryName()).
                        snippet(details.get(i).getStateName()));
               count++;

            }
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                // Getting reference to the TextView to set latitude
                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);

                // Getting reference to the TextView to set longitude
                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                TextView tvcem = (TextView) v.findViewById(R.id.tv_cemname);

                TextView tvcountry = (TextView) v.findViewById(R.id.tv_countryName);

                TextView tvstate = (TextView) v.findViewById(R.id.tv_stateName);

                TextView tvcounty = (TextView) v.findViewById(R.id.tv_countyName);

                TextView tvcity = (TextView) v.findViewById(R.id.tv_cityName);

                // Setting the latitude
                tvLat.setText("Latitude:" + arg0.getPosition());

                // Setting the longitude
                tvLng.setText("Longitude:"+ longx);

                // Setting the cemetery name
                tvcem.setText("Cemetery Name:"+ arg0.getTitle());

                tvcountry.setText("Country Name:"+ cemcountry);

                tvstate.setText("State Name:"+arg0.getSnippet());

                tvcounty.setText("County Name:"+cemcounty);

                tvcity.setText("City Name:"+cemcity);

                // Returning the view containing InfoWindow contents
                return v;

            }
        });


        if(count==0)
        {
            Toast.makeText(getApplicationContext(),"No cemeteries mapped",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),"Cemeteries found: "+count,Toast.LENGTH_SHORT).show();


    }
}
