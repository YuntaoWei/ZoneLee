package com.eysale.zonelee.app.ui;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eysale.zonelee.R;

public class BaseActivityToolbar {

    private static BaseActivityToolbar mInstance;

    private Button btnBack;
    private TextView tvTitle;
    private Button rightButton;

    private boolean showHomeAsUp = true;
    private boolean showTitle = true;
    private static Toolbar currentBar;
    private OnToolbarButtonClickListener buttonClickListener;

    private BaseActivityToolbar(Toolbar tool) {
        currentBar = tool;
        btnBack = tool.findViewById(R.id.toolbar_back);
        tvTitle = tool.findViewById(R.id.toolbar_title);
        rightButton = tool.findViewById(R.id.toolbar_right_button);
    }

    public static BaseActivityToolbar getToolBar(Toolbar tool) {
        if (mInstance == null || currentBar != tool) {
            mInstance = new BaseActivityToolbar(tool);
        }
        return mInstance;
    }

    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
            Log.i("tttt", "setTitle : " + title);
        }
    }

    public void setDisplayOptions(boolean showBack, boolean showTitle, boolean showRightBar) {
        if (btnBack == null || tvTitle == null) {
            throw new IllegalStateException("Toolbar error, should reInit!");
        }

        if (showBack)
            btnBack.setVisibility(View.VISIBLE);
        else
            btnBack.setVisibility(View.INVISIBLE);

        if (showTitle)
            tvTitle.setVisibility(View.INVISIBLE);
        else
            tvTitle.setVisibility(View.INVISIBLE);
    }

    public void setRightBarText(String text) {

    }

    public interface OnToolbarButtonClickListener {
        public void onClick(View v, boolean backBar);
    }

    public void setOnButtonClickListener(OnToolbarButtonClickListener cl) {
        buttonClickListener = cl;

        if (cl != null) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.toolbar_back:
                            buttonClickListener.onClick(v, true);
                            break;

                        case R.id.toolbar_right_button:
                            buttonClickListener.onClick(v, false);
                            break;
                    }
                }
            };
            btnBack.setOnClickListener(onClickListener);
            rightButton.setOnClickListener(onClickListener);
        }
    }

}
