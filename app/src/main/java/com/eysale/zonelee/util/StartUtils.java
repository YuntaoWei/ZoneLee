package com.eysale.zonelee.util;

import android.content.Context;
import android.content.Intent;

import com.eysale.zonelee.app.ContentActivity;

public class StartUtils {

    public static void startToMainPage(Context ctx) {
        ctx.startActivity(new Intent(ctx, ContentActivity.class));
    }

}
