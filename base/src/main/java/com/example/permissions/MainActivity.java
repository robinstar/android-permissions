package com.example.permissions;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the Vibrate button.
     */
    public void vibrate(View view) {
        TextView label = findViewById(R.id.textView);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        try {
            vibrator.vibrate(500L);
            label.setText(null);
        } catch (Exception e) {
            label.setText(e.getClass().getName());
        }
    }

    /**
     * Called when the user taps the Location button.
     */
    public void displayLocation(View view) {
        TextView label = findViewById(R.id.textView2);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            label.setText(location.getLatitude() + ", " + location.getLongitude());
        } catch (Exception e) {
            label.setText(e.getClass().getName());
        }
    }

    /**
     * Called when the user taps the Permissions button.
     */
    public void launchPermissions(View view) {
        Intent intent = new Intent("android.settings.APPLICATION_SETTINGS");
        intent.putExtra(Intent.EXTRA_PACKAGE_NAME, getPackageName());
        startActivity(intent);
    }
}
