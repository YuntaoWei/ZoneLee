package com.eysale.zonelee.presenter.fragmentview;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.Adapter.FoundPageListAdapter;
import com.eysale.zonelee.bean.FoundDatas;
import com.eysale.zonelee.bean.FoundPageDetailData;
import com.eysale.zonelee.model.DataManagerFoundModel;
import com.eysale.zonelee.model.LoadCallBack;
import com.eysale.zonelee.util.LogPrinter;
import com.kymjs.frame.view.AppDelegate;

import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class FoundFragmentDelegate extends AppDelegate implements WaveSwipeRefreshLayout.OnRefreshListener {

    WaveSwipeRefreshLayout refreshLayout;
    DataManagerFoundModel foundDataManager;
    LoadCallBack foundPageDataCallback;
    ListView mList;
    FoundPageListAdapter foundPageListAdapter;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    public void initWidget() {
        refreshLayout = get(R.id.main_swipe);
        refreshLayout.setOnRefreshListener(this);
        mList = get(R.id.main_list);
        foundPageListAdapter = new FoundPageListAdapter(null, LayoutInflater.from(getActivity()));
        mList.setAdapter(foundPageListAdapter);
    }

    public void startLoad() {
        LogPrinter.i("ttt", "FoundFragment Delegate startLoad.");

        if (foundDataManager == null) {
            foundDataManager = DataManagerFoundModel.getInstance();
            foundPageDataCallback = new LoadCallBack<FoundDatas>() {

                @Override
                public void onLoadStart() {
                }

                @Override
                public void onLoadComplete(FoundDatas data) {
                    refresh(data.getDatas());
                    LogPrinter.i("ttt", "onLoadComplete : " + data.getDatas().size());
                }
            };

            foundDataManager.addLoadCallback(foundPageDataCallback);
        }

        foundDataManager.start();
    }

    private void refresh(final List<FoundPageDetailData> datas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                if (foundPageListAdapter != null)
                    foundPageListAdapter.addData(datas);
            }
        });
    }

    @Override
    public void onRefresh() {
        new Thread() {
            @Override
            public void run() {
                foundDataManager.nextPage();
            }
        }.start();
    }

}
