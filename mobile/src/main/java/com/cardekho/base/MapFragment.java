package com.cardekho.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cardekho.R;
import com.cardekho.model.DataModel;
import com.cardekho.model.MyItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.InfoWindowAdapter{

    static DataModel dataModel;
    private ClusterManager<MyItem> mClusterManager;
    private MyItem clickedClusterItem;

    public static MapFragment newInstance(DataModel model) {
        MapFragment f = new MapFragment();
        dataModel = model;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        setUpMapIfNeeded();
        return rootView;
    }

    private void setUpMapIfNeeded() {
        SupportMapFragment supportMapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map));
        supportMapFragment.getMapAsync(this);
    }


    protected void startDemo(GoogleMap mMap) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<MyItem>(getActivity(), mMap);
        mMap.setOnCameraChangeListener(mClusterManager);

        try {
            readItems(mMap);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems(GoogleMap mMap) throws JSONException {
        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(this);
        mMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager
                .setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                    @Override
                    public boolean onClusterItemClick(MyItem item) {
                        clickedClusterItem = item;
                        return false;
                    }
                });

        List<MyItem> items = new ArrayList<MyItem>();
        List<DataModel.CityList> list = dataModel.getCityList();
        for(int i = 0; i < list.size(); i++)
        {
            double lat = list.get(i).getCoord().getLat();
            double lon = list.get(i).getCoord().getLon();
            String city = list.get(i).getName();
            String country = list.get(i).getCountry();
            MyItem myItem = new MyItem(lat, lon, city, country);
            items.add(myItem);
        }
        mClusterManager.addItems(items);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        startDemo(googleMap);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        marker.setTitle(clickedClusterItem.getCity());
        marker.setSnippet(clickedClusterItem.getCountry());
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


}
