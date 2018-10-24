package com.eysale.zonelee.util;

import android.util.Log;

public class LogPrinter {

    public static final boolean DEBUG = true;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_WARNING = 3;
    public static final int LOG_LEVEL_ERROR = 4;
    public static final int LOG_LEVEL_DEFAULT = 1;

    public static void i(String tag, String msg) {
        if(!DEBUG || LOG_LEVEL_DEFAULT > LOG_LEVEL_INFO)
            return;
        Log.i(tag, msg);
    }

    public static void i_withTrace(String tag, String msg) {
        Log.i(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void d(String tag, String msg) {
        if(!DEBUG || LOG_LEVEL_DEFAULT > LOG_LEVEL_DEBUG)
            return;
        Log.d(tag, msg);
    }

    public static void d_withTrace(String tag, String msg) {
        Log.d(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void w(String tag, String msg) {
        if(!DEBUG || LOG_LEVEL_DEFAULT > LOG_LEVEL_WARNING)
            return;
        Log.w(tag, msg);
    }

    public static void w_withTrace(String tag, String msg) {
        Log.w(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void e(String tag, String msg) {
        if(!DEBUG || LOG_LEVEL_DEFAULT > LOG_LEVEL_ERROR)
            return;
        Log.e(tag, msg);
    }

    public static void e_withTrace(String tag, String msg) {
        Log.e(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

}
