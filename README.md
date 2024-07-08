# Permission Library

**This project demonstrates the usage of a simple permission library (`MyPermissionLibrary`) for handling runtime permissions in Android applications.**



## Overview

The `MyPermissionLibrary` simplifies the process of requesting multiple runtime permissions in an Android app. It provides a fluent API to request permissions and handle the user's response asynchronously.



## Features

- Requesting multiple permissions (`WRITE_EXTERNAL_STORAGE`, `ACCESS_FINE_LOCATION`, `CAMERA`) with a single method call.
- Handling permission callbacks (`onGuarantee`, `onDeny`, `onFinish`) to manage permission outcomes effectively.


## Video
https://github.com/danielshitrit/HW2-PermissionLibrary/assets/117349965/d63f1e90-6027-4dfd-bb73-a8a48cbce652



## Usage

To integrate `MyPermissionLibrary` into your Android project:

1. **Initialization**: Create an instance of `PermissionManager` with a `Context`.

    ```java
    PermissionManager permissionManager = PermissionManager.create(context);
    ```

2. **Permission Request**: Request permissions using the fluent API.

    ```java
    permissionManager
        .title("Permission Request")
        .msg("This app requires the following permissions")
        .permissions(permissionItems)
        .checkMultiplePermissions(permissionCallback);
    ```

3. **Handling Permissions**: Implement `PermissionCallback` to handle permission results.

    ```java
    PermissionCallback permissionCallback = new PermissionCallback() {
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
    };
    ```



## Example

The `MainActivity` in this demo requests permissions when a button is clicked. It demonstrates how to integrate and use `MyPermissionLibrary`.

```java
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
