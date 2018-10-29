package com.eysale.zonelee.app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ContentViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> pageFragments;

    public ContentViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        pageFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return pageFragments.get(i);
    }

    @Override
    public int getCount() {
        return pageFragments.size();
    }
}
