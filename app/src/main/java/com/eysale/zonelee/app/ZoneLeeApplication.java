package com.eysale.zonelee.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.eysale.zonelee.bean.User;

import java.util.Locale;

public class ZoneLeeApplication extends Application {

    private User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void registUser(User u) {
        mUser = u;
    }

    public void unregistUser() {
        mUser = null;
    }

    public User getCurrentUser() {
        return mUser;
    }

}
