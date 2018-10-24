package com.eysale.zonelee.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class DrawableEdittext extends LinearLayout {

    public DrawableEdittext(Context context) {
        this(context, null);
    }

    public DrawableEdittext(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DrawableEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context ctx) {
    }

}
