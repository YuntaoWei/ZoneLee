package com.eysale.zonelee.app;

import android.app.Application;

import java.util.Locale;

public class ZoneLeeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        getResources().getConfiguration().setLocale(Locale.CHINA);
    }



}
