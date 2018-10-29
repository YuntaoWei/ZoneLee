package com.eysale.zonelee.app.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePermissionActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
    }

    private void checkPermission() {
        String[] permissions = onGetPermissions();
        boolean shouldRequestPermission = false;
        List<String> noPermissions = new ArrayList<>();
        for (String p : permissions
             ) {
            if(ActivityCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                shouldRequestPermission = true;
                noPermissions.add(p);
            }
        }

        if(shouldRequestPermission) {
            String[] needRequestPermissions = new String[noPermissions.size()];
            noPermissions.toArray(needRequestPermissions);
            ActivityCompat.requestPermissions(this, needRequestPermissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CODE) {
            for (int r : grantResults
                 ) {
                if(r == PackageManager.PERMISSION_DENIED) {
                    onGetPermissionFailure();
                    break;
                }
            }

            onGetPermissonSuccess();
        }
    }

    protected abstract String[] onGetPermissions();

    protected abstract void onGetPermissonSuccess();

    protected abstract void onGetPermissionFailure();

}
