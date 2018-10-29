package com.eysale.zonelee.app.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.eysale.zonelee.R;
import com.kymjs.frame.view.AppDelegate;

public class ContentActivityDelegate extends AppDelegate {

    private ViewPager contentViewPager;
    private PagerAdapter pagerAdapter;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    @Override
    public void initWidget() {
        contentViewPager = get(R.id.contentPager);
    }
}
