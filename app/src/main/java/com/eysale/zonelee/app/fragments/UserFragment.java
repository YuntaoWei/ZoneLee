package com.eysale.zonelee.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.eysale.zonelee.R;
import com.eysale.zonelee.presenter.fragmentview.UserFragmentDelegate;

import butterknife.OnClick;

public class UserFragment extends BaseFragment<UserFragmentDelegate> implements View.OnClickListener {

    public UserFragment() {
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private UserFragmentDelegate getDelegate() {
        return (UserFragmentDelegate)viewDelegate;
    }

    @Override
    protected void bindEvenListener() {
        getDelegate().setOnClickListener(this, R.id.bt_add_tag);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_tag:
                getDelegate().onAddTagButtonClick();
                break;
        }
    }
}
