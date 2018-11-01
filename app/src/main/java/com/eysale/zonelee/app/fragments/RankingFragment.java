package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;

import com.eysale.zonelee.presenter.fragmentview.RankingFragmentDelegate;

public class RankingFragment extends BaseFragment<RankingFragmentDelegate> {

    public RankingFragment() {
    }

    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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
        return RankingFragmentDelegate.class;
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
