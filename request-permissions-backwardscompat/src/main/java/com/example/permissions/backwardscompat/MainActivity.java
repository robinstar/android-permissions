package com.example.permissions.backwardscompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            final int status = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (status == PackageManager.PERMISSION_GRANTED) {
                displayCurrentFineLocationPermissionStatus(status);
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
            }
            return;
        }

        displayCurrentFineLocationPermissionStatus(PackageManager.PERMISSION_GRANTED);
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
     * Called when the user taps the Permissions button.
     */
    public void launchPermissions(View view) {
        IntentUtils.launchPackageDetails(this, getPackageName());
    }
}
