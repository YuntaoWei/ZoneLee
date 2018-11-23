package com.eysale.zonelee.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class BasicUtil {
    //email format verify.
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String REGEX_EMAIL1 = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    public static final String APP_DIR = "ZoneLee";

    public static final String PUBLIC_IMAGE_NAME = "zo-";

    public static boolean emailFormatIllegal(String email) {
        if(TextUtils.isEmpty(email)) {
            return false;
        }
        return Pattern.matches(REGEX_EMAIL1, email);
    }

    public static Point getScreenSize(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        Log.i("tttt", "getScreenSize : " + size);
        return size;
    }

    public static Uri getTmpPictureUri(Context ctx) {
        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + File.separator + APP_DIR;
        File parent = new File(dir);
        if(!parent.exists())
            parent.mkdirs();

        File f = new File(parent, "tmp_" + System.currentTimeMillis() + ".jpg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(f);
    }

    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.x;
    }

    public static Bitmap scaleBitmap(Bitmap bm, float scale) {
        int scaleWidth = (int) (bm.getWidth() * scale);
        int scaleHeight = (int) (bm.getHeight() * scale);
        return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, false);
    }

}
