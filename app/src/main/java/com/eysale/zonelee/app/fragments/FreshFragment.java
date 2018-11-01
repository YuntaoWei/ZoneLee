package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;

import com.eysale.zonelee.presenter.fragmentview.FreshFragmentDelegate;

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
