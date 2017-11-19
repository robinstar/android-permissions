package com.example.core;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by yueweiwei on 19/11/2017.
 */

public class PermissionUtils {

    public static CharSequence getDisplayStatus(Context context, int status) {
        switch (status) {
            case PackageManager.PERMISSION_GRANTED:
                return "PERMISSION_GRANTED";
            case PackageManager.PERMISSION_DENIED:
                return "PERMISSION_DENIED";
            default:
                return context.getString(android.R.string.unknownName);
        }
    }
}
