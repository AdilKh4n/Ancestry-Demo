package com.example.adilkhan.ancestry_demo;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by adilkhan on 3/30/17.
 */

public class CemeteryDetails implements Serializable {
    private int cemeteryId;
    private String cemeteryName;
    private String latitude;
    private String longitude;

    public int getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(int cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public CemeteryDetails(int cemeteryId, String cemeteryName, String latitude, String longitude) {

        this.cemeteryId = cemeteryId;
        this.cemeteryName = cemeteryName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
