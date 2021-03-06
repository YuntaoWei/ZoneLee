package com.eysale.zonelee.app;

import android.view.View;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.fragments.PageUtil;
import com.eysale.zonelee.presenter.activityview.ContentActivityDelegate;
import com.eysale.zonelee.request.RxUtils;
import com.eysale.zonelee.response.BaseResponse;
import com.kymjs.frame.presenter.ActivityPresenter;

import io.reactivex.functions.Consumer;

public class ContentActivity extends ActivityPresenter<ContentActivityDelegate> implements View.OnClickListener {

    @Override
    protected void bindEvenListener() {
        viewDelegate.setOnClickListener(this,
                R.id.toolbar_back,//Toolbar返回按钮
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
        int index = -1;
        switch (v.getId()) {
            case R.id.toolbar_back:
                loginOut();
                break;

            case R.id.btn_content_found:
                index = PageUtil.INDEX_FOUND;
                break;

            case R.id.btn_content_ranking:
                index = PageUtil.INDEX_RANKING;
                break;

            case R.id.btn_content_follow:
                index = PageUtil.INDEX_FOLLOW;
                break;

            case R.id.btn_content_fresh:
                index = PageUtil.INDEX_FRESH;
                break;

            case R.id.btn_content_user:
                index = PageUtil.INDEX_USER;
                break;

        }

        if(index != -1) {
            viewDelegate.changePageIndex(index);
        }

        /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/
    }

    public void setActionBarDisplayOption(boolean back, boolean title) {
        viewDelegate.getContentActivityToolbar().setDisplayOptions(back, title, false);
    }

    public void setTitle(String s) {
        viewDelegate.getContentActivityToolbar().setTitle(s);
    }

    public void setTitle(int res) {
        setTitle(getString(res));
    }

    private void loginOut() {
        ZoneLeeApplication app = (ZoneLeeApplication) getApplication();
        if(app.getCurrentUser() == null) {
            logoutException();
            return;
        }
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
