package com.eysale.zonelee.app;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.view.ContentActivityDelegate;
import com.eysale.zonelee.request.RxUtils;
import com.eysale.zonelee.response.BaseResponse;
import com.kymjs.frame.presenter.ActivityPresenter;

import io.reactivex.functions.Consumer;

public class ContentActivity extends ActivityPresenter<ContentActivityDelegate> implements View.OnClickListener {

    @Override
    protected void bindEvenListener() {
        viewDelegate.setOnClickListener(this,
                R.id.btn_content_found,//发现
                R.id.btn_content_follow,//关注
                R.id.btn_content_ranking,//排行
                R.id.btn_content_fresh,//猎奇
                R.id.btn_content_user);//我的
    }

    @Override
    protected Class<ContentActivityDelegate> getDelegateClass() {
        return ContentActivityDelegate.class;
    }

    @Override
    public void onClick(View v) {
        loginOut();
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void loginOut() {
        ZoneLeeApplication app = (ZoneLeeApplication) getApplication();
        RxUtils.loginOut(app.getCurrentUser().userId, new Consumer<BaseResponse>() {
            @Override
            public void accept(BaseResponse baseResponse) throws Exception {
                if (baseResponse != null && baseResponse.code.equals("success")) {
                    logoutSuccess();
                } else {
                    logoutException();
                }
            }
        });
    }

    private void logoutException() {
        ZoneLeeApplication app = (ZoneLeeApplication) getApplication();
        app.unregistUser();
        finish();
    }

    private void logoutSuccess() {
        ZoneLeeApplication app = (ZoneLeeApplication) getApplication();
        app.unregistUser();
        finish();
    }
}
