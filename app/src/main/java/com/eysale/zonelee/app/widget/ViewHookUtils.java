package com.eysale.zonelee.app.widget;

import android.util.Log;
import android.widget.LinearLayout;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewHookUtils {

    public static boolean hookDialogTitleColor(NiftyDialogBuilder dialog, int color) {
        Class clazz = dialog.getClass();
        try {
            Field topView = clazz.getDeclaredField("mLinearLayoutTopView");
            topView.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) topView.get(dialog);
            linearLayout.setBackgroundColor(color);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            return true;
        }
    }

}
