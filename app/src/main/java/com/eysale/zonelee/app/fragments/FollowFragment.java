package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;

import com.eysale.zonelee.app.view.fragmentview.FollowFragmentDelegate;

public class FollowFragment extends BaseFragment<FollowFragmentDelegate> {

    public FollowFragment() {
    }

    public static FollowFragment newInstance(String param1, String param2) {
        FollowFragment fragment = new FollowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFragmentVisibleChange(boolean visible) {

    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    protected Class<FollowFragmentDelegate> getDelegateClass() {
        return FollowFragmentDelegate.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
