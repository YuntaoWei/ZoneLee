package com.eysale.zonelee.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.eysale.zonelee.util.LogPrinter;
import com.kymjs.frame.presenter.FragmentPresenter;
import com.kymjs.frame.view.IDelegate;

public abstract class BaseFragment<T extends IDelegate> extends FragmentPresenter {

    private static final String TAG = "BaseFragment";

    private boolean isFragmentVisible;
    private boolean isFirstVisible;
    private View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogPrinter.i(TAG, "setUserVisibleHint : " + isFirstVisible + "   " + isFragmentVisible + "    rootView : " + rootView);
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initState();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(rootView, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initState();
    }

    private void initState() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
    }

    protected abstract void onFragmentVisibleChange(boolean visible);

    protected void onFragmentFirstVisible() {}

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

}
