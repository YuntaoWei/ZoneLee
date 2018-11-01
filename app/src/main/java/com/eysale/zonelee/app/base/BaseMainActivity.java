package com.eysale.zonelee.app.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.ZoneLeeApplication;
import com.eysale.zonelee.request.StarterUtils;
import com.eysale.zonelee.response.LoginResponse;

public class BaseMainActivity extends AppCompatActivity {

    protected void loginFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    protected void registerFailed(String s) {
        Toast.makeText(this, getString(R.string.regist_failed), Toast.LENGTH_SHORT).show();
    }

    protected void loginSuccess(LoginResponse u) {
        ((ZoneLeeApplication)getApplication()).registUser(u.getContent());
        loginSuccess();
    }

    protected void loginSuccess() {
        StarterUtils.startToMainPage(this);
        finish();
    }

}
