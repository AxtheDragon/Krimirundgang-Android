package de.hottenstein.krimirundgang;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TourMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String sLocationName;
    private float randMin = -10;
    private float randMax = +10;
    private Random rand = new Random();
    private List<StopInfo> stoplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_map);
        stoplist = createList(10);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private List<StopInfo> createList(int size) {

        List<StopInfo> result = new ArrayList<StopInfo>();
        for (int i=1; i <= size; i++){
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
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Add a marker at the location of the first stop
        for (int i = 0; i < stoplist.size(); i++) {
            double lat = stoplist.get(i).location.getLatitude();
            double lng = stoplist.get(i).location.getLongitude();
            LatLng stop = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(stop).title(stoplist.get(i).title));
        }

    }
}