package com.eysale.zonelee.app;

import android.app.Application;

import com.eysale.zonelee.response.User;

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
