package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;

import com.eysale.zonelee.presenter.fragmentview.UserFragmentDelegate;

public class UserFragment extends BaseFragment<UserFragmentDelegate> {

    public UserFragment() {
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        return UserFragmentDelegate.class;
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
