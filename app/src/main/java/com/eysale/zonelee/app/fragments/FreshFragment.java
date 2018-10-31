package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.view.fragmentview.FreshFragmentDelegate;

public class FreshFragment extends BaseFragment<FreshFragmentDelegate> {

    public FreshFragment() {
    }

    public static FreshFragment newInstance(String param1, String param2) {
        FreshFragment fragment = new FreshFragment();
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
    protected Class getDelegateClass() {
        return FreshFragmentDelegate.class;
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
