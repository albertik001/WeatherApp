package com.geektech.weatherapp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.geektech.weatherapp.R;
import com.geektech.weatherapp.data.local.db.prefs.Prefs;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private Prefs prefs;
    private LocationManager locationManager;
    private final String[] PERMISSION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
        prefs = new Prefs(this);
    }

    private void initMap() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        ActivityCompat.requestPermissions(this, PERMISSION, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                    &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation();
            }
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 0, Location::reset);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        prefs.prefsCash();
        Log.e("TAG", "callRequests: 999");
    }

}