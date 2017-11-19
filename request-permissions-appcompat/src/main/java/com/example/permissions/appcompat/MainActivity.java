package com.example.permissions.appcompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.core.IntentUtils;
import com.example.core.PermissionUtils;

public class MainActivity extends Activity {

    private static final int REQUEST_FINE_LOCATION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the Location button.
     */
    public void requestFineLocationPermission(View view) {
        final int status = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (status == PackageManager.PERMISSION_GRANTED) {
            displayCurrentFineLocationPermissionStatus(status);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION:
                TextView textView = findViewById(R.id.permission_status);
                if (grantResults.length == 0) {
                    textView.setText(android.R.string.unknownName);
                    break;
                }

                displayCurrentFineLocationPermissionStatus(grantResults[0]);
                break;
        }
    }

    private void displayCurrentFineLocationPermissionStatus(int status) {
        TextView textView = findViewById(R.id.permission_status);
        textView.setText(PermissionUtils.getDisplayStatus(this, status));
    }

    /**
     * Called when the user taps the Fine Permission button.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void checkFineLocationPermission(View view) {
        final int status = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        String text = PermissionUtils.getDisplayStatus(this, status).toString();
        TextView textView = findViewById(R.id.fine_location_status);
        textView.setText(text);
    }

    /**
     * Called when the user taps the Coarse Permission button.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void checkCoarseLocationPermission(View view) {
        final int status = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        String text = PermissionUtils.getDisplayStatus(this, status).toString();
        TextView textView = findViewById(R.id.coarse_location_status);
        textView.setText(text);
    }

    /**
     * Called when the user taps the Permissions button.
     */
    public void launchPermissions(View view) {
        IntentUtils.launchPackageDetails(this, getPackageName());
    }
}
