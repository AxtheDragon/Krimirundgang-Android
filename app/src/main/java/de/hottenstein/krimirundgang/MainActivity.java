package de.hottenstein.krimirundgang;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "de.hottenstein.krimirundgang.MESSAGE";

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    /**
     * Constant used to identify the location settings dialog. Must be positive.
     */
    private static final int REQUEST_CHECK_SETTINGS = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Callback for Location events.
     */
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationRequest mLocationRequest;
    private SettingsClient mSettingsClient;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;

    @Override
    public void onStart() {
        super.onStart();

        if (!isPermitted()) {
            requestPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {
                    stopLocationUpdates();
                }
                return;
            }
        }
    }

    public void enterTourOverview(View view) {
        Intent intent = new Intent(this, TourDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView tourCycler = (RecyclerView) findViewById(R.id.tourCycler);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        mLocationCallback = createLocationCallback();
        mLocationRequest = createLocationRequest();
        mLocationSettingsRequest = buildLocationSettingsRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isPermitted()) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    /**
     * Requests location updates from the FusedLocationApi. Note: we don't call this unless location
     * runtime permission has been granted.
     */
    private void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                                                    mLocationCallback,
                                                                    Looper.myLooper());
                        displayLastLocation();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) { }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = getString(R.string.settingsChangeUnavailable);
                                Toast.makeText(MainActivity.this,
                                               errorMessage,
                                               Toast.LENGTH_LONG).show();
                        }
                        displayLastLocation();
                    }
                });
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    /**
     * Uses a {@link com.google.android.gms.location.LocationSettingsRequest.Builder} to build
     * a {@link com.google.android.gms.location.LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    private LocationSettingsRequest buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        return builder.build();
    }

    /**
     * Creates a callback for receiving location events.
     */
    private LocationCallback createLocationCallback() {
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mLastLocation = locationResult.getLastLocation();
                displayLastLocation();
            }
        };
    }

    @SuppressWarnings("MissingPermission")
    private void displayLastLocation() {
        TextView mLatitudeText = (TextView) findViewById(R.id.LatitudeText);
        TextView mLongitudeText = (TextView) findViewById(R.id.LongitudeText);

        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                                          new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                                       Manifest.permission.ACCESS_COARSE_LOCATION},
                                          MY_PERMISSIONS_REQUEST_LOCATION);
    }

    private boolean isPermitted() {
        return isAllowedToAccessLocation(Manifest.permission.ACCESS_FINE_LOCATION)
                && isAllowedToAccessLocation(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private boolean isAllowedToAccessLocation(String locationGranularity) {
        return ActivityCompat.checkSelfPermission(this, locationGranularity) == PackageManager.PERMISSION_GRANTED;
    }
}
