package com.example.pengenalangps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Lokasiku extends Activity {
    private LocationManager lm;
    private LocationListener locListener;
    private TextView latTxt, lonTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latTxt = (TextView) findViewById(R.id.latitudeTxt);
        lonTxt = (TextView) findViewById(R.id.longitudeTxt);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locListener = new MyLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 200, locListener);
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location loc) {
            if (loc != null) {
                latTxt.setText(String.valueOf(loc.getLatitude()));
                lonTxt.setText(String.valueOf(loc.getLongitude()));
                Toast.makeText(getBaseContext(),
                        "Location Changed : Lat" + loc.getLatitude() +
                                "lgt: " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {}

        @Override
        public void onProviderEnabled(@NonNull String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
