package com.cardekho.model;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private String city;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    private String country;

    public MyItem(double lat, double lng, String city, String country) {
        mPosition = new LatLng(lat, lng);
        this.city = city;
        this.country = country;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}