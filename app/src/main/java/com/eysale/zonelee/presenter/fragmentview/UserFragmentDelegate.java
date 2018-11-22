package com.eysale.zonelee.presenter.fragmentview;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.eysale.zonelee.R;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;

public class UserFragmentDelegate extends AppDelegate {

    TagContainerLayout mTagContainerLayout;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initWidget() {
    }

    public void onAddTagButtonClick() {
    }

}
