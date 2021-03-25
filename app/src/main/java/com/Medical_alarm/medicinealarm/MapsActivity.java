package com.example.medicinealarm;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        LatLng LSUHealth = new LatLng(32.48114905, -93.76078556437238);
        mMap.addMarker(new MarkerOptions().position(LSUHealth).title("LSUS Health of Shreveport"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LSUHealth));

        LatLng willknigh = new LatLng(32.432, -93.7089);
        mMap.addMarker(new MarkerOptions().position(willknigh).title("Willis Knighton of South for Women health"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(willknigh));

        LatLng Christus = new LatLng(32.42953, -93.71690);
        mMap.addMarker(new MarkerOptions().position(Christus).title("Chistus Shreveport- Bossier Health System"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Christus));

        LatLng Specialist = new LatLng(32.51016, -93.74567);
        mMap.addMarker(new MarkerOptions().position(Specialist).title("Cornerstone Speciality hospitals Bossier City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Specialist));

        LatLng Physician = new LatLng(32.48550, -93.77101);
        mMap.addMarker(new MarkerOptions().position(Physician).title("Pam Speciality Hospital of Shreveport South"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Physician));

        LatLng WKBosser = new LatLng(32.56647, -93.72153);
        mMap.addMarker(new MarkerOptions().position(WKBosser).title("Willis Knighton Health System Bossier City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(WKBosser));

    }
}