package com.eysale.zonelee.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.base.BaseMainActivity;
import com.eysale.zonelee.bean.User;
import com.eysale.zonelee.response.LoginResponse;
import com.eysale.zonelee.util.LogPrinter;
import com.eysale.zonelee.util.NetWorkUtils;
import com.eysale.zonelee.request.RxUtils;
import com.eysale.zonelee.request.StartUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseMainActivity implements View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.login_bt_login)
    Button btnLogin;
    @BindView(R.id.login_bt_regist)
    Button btnRegist;
    @BindView(R.id.login_et_username)
    EditText etUserName;
    @BindView(R.id.login_et_password)
    EditText etPassword;
    @BindView(R.id.password_layout)
    LinearLayout passwordLayout;
    @BindView(R.id.username_layout)
    LinearLayout userNameLayout;
    @BindView(R.id.img_username)
    ImageView imgUser;
    @BindView(R.id.img_password)
    ImageView imgPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
        etPassword.setOnFocusChangeListener(this);
        etUserName.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_bt_login:
                if(true) {
                    loginSuccess();
                    return;
                }
                String user = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                LogPrinter.i("ttt", "Login clicked : " + user + "   " + password);
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, R.string.username_password_null, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!NetWorkUtils.canAccessNetwork(this)) {
                    Toast.makeText(this, R.string.network_not_access, Toast.LENGTH_SHORT).show();
                    return;
                }

                RxUtils.login(user, password, new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse u) throws Exception {
                        if(u != null && u.code.equals("success")) {
                            loginSuccess(u);
                        } else {
                            loginFailed(u == null ? "error" : u.message);
                        }

                    }
                });
                break;

            case R.id.login_bt_regist:
                startActivity(new Intent(this, RegistActivity.class));
                break;

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_et_password:
                changeLayoutBackground(false);
                break;

            case R.id.login_et_username:
                changeLayoutBackground(true);
                break;
        }
    }

    private void changeLayoutBackground(boolean usernameLayout) {
        if(usernameLayout) {
            userNameLayout.setBackgroundResource(R.drawable.login_layout_select);
            imgUser.setImageResource(R.mipmap.user_select);

            passwordLayout.setBackgroundResource(R.drawable.login_layout_deselect);
            imgPassword.setImageResource(R.mipmap.password_deselect);
        } else {
            userNameLayout.setBackgroundResource(R.drawable.login_layout_deselect);
            imgUser.setImageResource(R.mipmap.user_deselect);

            passwordLayout.setBackgroundResource(R.drawable.login_layout_select);
            imgPassword.setImageResource(R.mipmap.password_select);
        }
    }

}
