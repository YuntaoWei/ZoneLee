package com.eysale.zonelee.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BasePermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void checkPermission() {

    }

    protected abstract String[] onGetPermissions();

    protected abstract void onGetPermissonSuccess();

    protected abstract void onGetPermissionFailure();

}
