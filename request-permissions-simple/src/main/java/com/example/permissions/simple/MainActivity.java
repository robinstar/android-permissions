package com.example.permissions.simple;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
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
    @TargetApi(Build.VERSION_CODES.M)
    public void requestFineLocationPermission(View view) {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
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

                final int status = grantResults[0];
                textView.setText(PermissionUtils.getDisplayStatus(this, status));
                break;
        }
    }

    /**
     * Called when the user taps the Permissions button.
     */
    public void launchPermissions(View view) {
        IntentUtils.launchPackageDetails(this, getPackageName());
    }
}
