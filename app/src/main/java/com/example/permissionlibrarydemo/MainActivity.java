
package com.example.permissionlibrarydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import com.example.mypermissionlibrary.PermissionCallback;
import com.example.mypermissionlibrary.PermissionItem;
import com.example.mypermissionlibrary.PermissionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRequestPermission = findViewById(R.id.btnRequestPermission);
        btnRequestPermission.setOnClickListener(v -> requestPermissions());
    }

    private void requestPermissions() {
        // Check if permissions are granted
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // Request permissions
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);
        } else {
            // All permissions are already granted
            proceedWithAction();
        }
    }

    private void proceedWithAction() {
        // Perform actions that require permissions
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
        permissionItems.add(new PermissionItem(android.Manifest.permission.ACCESS_FINE_LOCATION));
        permissionItems.add(new PermissionItem(android.Manifest.permission.CAMERA));

        PermissionManager.create(this)
                .title("Permission Request")
                .msg("This app requires the following permissions")
                .permissions(Collections.singletonList((PermissionItem) permissionItems))
                .checkMultiplePermissions(new PermissionCallback() {
                    @Override
                    public void onGuarantee(String permission, int position) {
                        // Permission granted
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        // Permission denied
                    }

                    @Override
                    public void onFinish() {
                        // All permissions checked
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Check if all permissions were granted
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                // Permissions granted, proceed with action
                proceedWithAction();
            } else {
                // Permissions denied
                // Handle permission denial
            }
        }
    }
}

