package com.example.mypermissionlibrary;

import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;

public class PermissionActivity extends Activity {
    public static final int PERMISSION_TYPE_SINGLE = 1;
    private static PermissionCallback callback;

    public static void setCallBack(PermissionCallback callback) {
        PermissionActivity.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle permission logic here
        // For simplicity, we're finishing the activity immediately
        if (callback != null) {
            callback.onFinish();
        }
        finish();
    }
}
