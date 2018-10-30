package com.eysale.zonelee.app.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eysale.zonelee.R;

public class ContentActivityToolbar {

    private static ContentActivityToolbar mInstance;

    private Button btnBack;
    private TextView tvTitle;

    private boolean showHomeAsUp = true;
    private boolean showTitle = true;

    private ContentActivityToolbar(Toolbar tool) {
        btnBack = tool.findViewById(R.id.toolbar_back);
        tvTitle = tool.findViewById(R.id.toolbar_title);
    }

    public static ContentActivityToolbar getToolBar(Toolbar tool) {
        if(mInstance == null) {
            mInstance = new ContentActivityToolbar(tool);
        }
        return mInstance;
    }

    public void setTitle(String title) {
        if(tvTitle != null)
            tvTitle.setText(title);
    }

    public void setDisplayOptions(boolean showBack, boolean showTitle) {
        if(btnBack == null || tvTitle == null) {
            throw new IllegalStateException("Toolbar error, should reInit!");
        }

        if(showBack)
            btnBack.setVisibility(View.VISIBLE);
        else
            btnBack.setVisibility(View.INVISIBLE);

        if(showTitle)
            tvTitle.setVisibility(View.INVISIBLE);
        else
            tvTitle.setVisibility(View.INVISIBLE);
    }

}
