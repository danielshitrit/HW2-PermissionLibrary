package com.example.mypermissionlibrary;

import java.io.Serializable;

public class PermissionItem implements Serializable {
    private String permission;
    private String name;
    private int iconRes;

    public PermissionItem(String permission) {
        this.permission = permission;
    }

    public PermissionItem(String permission, String name, int iconRes) {
        this.permission = permission;
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public int getIconRes() {
        return iconRes;
    }
}
