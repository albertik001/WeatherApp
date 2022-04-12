package com.geektech.weatherapp.ui.googlemap;

import android.location.Location;
import android.location.LocationListener;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.geektech.weatherapp.R;
import com.geektech.weatherapp.base.BaseFragment;
import com.geektech.weatherapp.databinding.FragmentMapBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MapFragment extends BaseFragment<FragmentMapBinding> implements OnMapReadyCallback, LocationListener {
    private GoogleMap gMap;
    private ArrayList<LatLng> markerList = new ArrayList<>();


    @Override
    public FragmentMapBinding bind() {
        return FragmentMapBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupViews() {
        initMap();
    }

    protected void setupOnclick() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void callRequests() {

    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (gMap != null) {
            gMap.addMarker(new MarkerOptions().position(latLng));
            gMap.animateCamera(animateMapCamera(latLng, 4f, 60f, 30f));
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        setupMapListener();
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.draggable(true);
                gMap.clear();
                gMap.addMarker(markerOptions);
                markerList.add(latLng);
                gMap.animateCamera(animateMapCamera(
                        latLng,
                        5f,
                        90f,
                        40f
                        )
                );
                Toast.makeText(requireContext(), latLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupMapListener() {
        if (gMap != null) {
            gMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDrag(@NonNull Marker marker) {
                    Log.e("TAG", "onMarkerDrag: " + marker.getPosition());
                }

                @Override
                public void onMarkerDragEnd(@NonNull Marker marker) {
                    Log.e("TAG", "onMarkerDrag: End " + marker.getPosition());
                }

                @Override
                public void onMarkerDragStart(@NonNull Marker marker) {
                    Log.e("TAG", "onMarkerDrag: Start" + marker.getPosition());
                }
            });
            gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    marker.setTitle("Coordinate: " + marker.getPosition());
                    marker.showInfoWindow();
                    return false;
                }
            });
            gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(@NonNull Marker marker) {
                    Toast.makeText(requireContext(), "Salam", Toast.LENGTH_SHORT).show();
                    double a = marker.getPosition().latitude;
                    double b = marker.getPosition().longitude;
                    controller.navigate(MapFragmentDirections.actionMapFragmentToWeatherFragment(a, b));
                }
            });
        }
    }

    private CameraUpdate animateMapCamera(LatLng latLng, float zoom, float bearing, float tilt) {
        return CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(zoom)
                        .bearing(bearing)
                        .tilt(tilt)
                        .build()
        );
    }

}