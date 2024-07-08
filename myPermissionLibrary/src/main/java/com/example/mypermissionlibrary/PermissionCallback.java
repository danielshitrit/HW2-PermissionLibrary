package com.example.mypermissionlibrary;

public interface PermissionCallback {
    void onGuarantee(String permission, int position);
    void onDeny(String permission, int position);
    void onFinish();
}
