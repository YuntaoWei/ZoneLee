package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.ContentActivity;
import com.eysale.zonelee.presenter.fragmentview.FoundFragmentDelegate;
import com.eysale.zonelee.util.LogPrinter;

public class FoundFragment extends BaseFragment<FoundFragmentDelegate> {

    public FoundFragment() {
    }

    public static FoundFragment newInstance(String param1, String param2) {
        FoundFragment fragment = new FoundFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFragmentFirstVisible() {
        LogPrinter.i("ttt", "FoundFragment onFragmentFirstVisible.");
        ((FoundFragmentDelegate)viewDelegate).startLoad();
    }

    @Override
    protected void onFragmentVisibleChange(boolean visible) {
        LogPrinter.i("ttt", "FoundFragment onFragmentVisibleChange : " + visible);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    protected Class getDelegateClass() {
        return FoundFragmentDelegate.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((ContentActivity)context).setActionBarDisplayOption(false, true);
        ((ContentActivity)context).setTitle(R.string.bottombar_home);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
