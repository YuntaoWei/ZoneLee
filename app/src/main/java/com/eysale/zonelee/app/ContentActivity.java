package com.eysale.zonelee.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eysale.zonelee.R;
import com.eysale.zonelee.request.RxUtils;
import com.eysale.zonelee.response.BaseResponse;

import io.reactivex.functions.Consumer;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOut();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void loginOut() {
        ZoneLeeApplication app = (ZoneLeeApplication) getApplication();
        RxUtils.loginOut(app.getCurrentUser().userId, new Consumer<BaseResponse>() {
            @Override
            public void accept(BaseResponse baseResponse) throws Exception {
                if(baseResponse != null && baseResponse.code.equals("success")) {
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
