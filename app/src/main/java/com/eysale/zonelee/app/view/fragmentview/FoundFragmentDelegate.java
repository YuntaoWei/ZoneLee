package com.eysale.zonelee.app.view.fragmentview;

import com.eysale.zonelee.R;
import com.kymjs.frame.view.AppDelegate;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class FoundFragmentDelegate extends AppDelegate implements WaveSwipeRefreshLayout.OnRefreshListener {

    WaveSwipeRefreshLayout refreshLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    public void initWidget() {
        refreshLayout = get(R.id.main_swipe);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
