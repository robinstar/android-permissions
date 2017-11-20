package com.example.permissions.rationale;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.core.IntentUtils;
import com.example.core.PermissionUtils;

public class MainActivity extends AppCompatActivity {

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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                showPermissionRationale();
            } else {
                requestFineLocation();
            }
        }
    }

    private void showPermissionRationale() {
        new AlertDialog.Builder(this)
                .setTitle("需要授权")
                .setMessage("请为该权限授权，否则应用无法提供响应的功能。")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestFineLocation();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .show();
    }

    private void requestFineLocation() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
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
