package de.hottenstein.krimirundgang;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TourMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private String sLocationName;
    private List<StopInfo> stopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_map);
        stopList = createList(10);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private List<StopInfo> createList(int size) {
        float randMin = -10;
        float randMax = +10;
        Random rand = new Random();

        List<StopInfo> result = new ArrayList<StopInfo>();
        for (int i = 1; i <= size; i++) {
            float latOffset = rand.nextFloat() * (randMin - randMax) + randMin;
            float lngOffset = rand.nextFloat() * (randMin - randMax) + randMin;
            StopInfo si = new StopInfo();
            si.title = StopInfo.TITLE_PREFIX + i;
            si.description = StopInfo.DESCRIPTION_PREFIX + i;
            si.location = new Location(sLocationName);
            si.location.setLatitude(52.0000 + latOffset);
            si.location.setLongitude(9.0000 + lngOffset);
            si.content = i + StopInfo.CONTENT_DUMMY;

            result.add(si);
        }
        return result;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        StopInfo currentStop;

        // Add a marker at the location of the first stop
        for (int i = 0; i < stopList.size(); i++) {
            currentStop = stopList.get(i);
            double lat = currentStop.location.getLatitude();
            double lng = currentStop.location.getLongitude();
            LatLng stop = new LatLng(lat, lng);
            Marker marker =  mMap.addMarker(new MarkerOptions()
                    .position(stop)
                    .title(currentStop.title)
                    .snippet(currentStop.description)
            );
            marker.setTag(currentStop);
            builder.include(stop);
        }
        mMap.setOnInfoWindowClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 50));
    }

   @Override
    public void onInfoWindowClick(Marker marker) {
       Intent intent = new Intent(this, StopDetailActivity.class);
       StopInfo si = (StopInfo) marker.getTag();
       intent.putExtra("stopInfo", si);
       startActivity(intent);
    }
}