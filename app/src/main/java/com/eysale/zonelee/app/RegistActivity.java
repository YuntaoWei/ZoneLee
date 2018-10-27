package com.eysale.zonelee.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eysale.zonelee.R;
import com.eysale.zonelee.app.base.BaseMainActivity;
import com.eysale.zonelee.request.RxUtils;
import com.eysale.zonelee.request.StartUtils;
import com.eysale.zonelee.response.BaseResponse;
import com.eysale.zonelee.response.LoginResponse;
import com.eysale.zonelee.util.BasicUtil;
import com.eysale.zonelee.util.LogPrinter;
import com.eysale.zonelee.util.NetWorkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RegistActivity extends BaseMainActivity implements View.OnClickListener {
    @BindView(R.id.et_phonenumber)
    EditText etPhoneNumber;
    @BindView(R.id.tv_verification)
    TextView tvGotVerify;
    @BindView(R.id.et_verification_code)
    EditText etVerification;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_regist_login)
    Button btnRegistLogin;

    int updateCount = 60;
    String user, password;

    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private Runnable updateVerificationCodeTextView = new Runnable() {
        @Override
        public void run() {
            updateCount--;
            if(updateCount < 0) {
                updateCount = 60;
                tvGotVerify.setEnabled(true);
                tvGotVerify.setText(R.string.get_verification_code);
                return;
            }

            tvGotVerify.setText(String.format(getString(R.string.verify_count_tips), updateCount));
            mainHandler.postDelayed(updateVerificationCodeTextView, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnRegistLogin.setOnClickListener(this);
        tvGotVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!NetWorkUtils.canAccessNetwork(this)) {
            Toast.makeText(this, R.string.network_not_access, Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()) {
            case R.id.tv_verification:
                user = etPhoneNumber.getText().toString();
                if(!BasicUtil.emailFormatIllegal(user)) {
                    Toast.makeText(this, R.string.email_error_tips, Toast.LENGTH_SHORT).show();
                    return;
                }

                RxUtils.getRegistVerificationCode(user, new Consumer<BaseResponse>() {

                    @Override
                    public void accept(BaseResponse registerResponse) throws Exception {
                        //success here.
                        gotVerificationCodeSuccess();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable registerResponse) throws Exception {
                        //failed here,cause by:network,server etc.
                        gotVerificationCodeFail();
                    }
                });
                break;

            case R.id.bt_regist_login:
                user = etPhoneNumber.getText().toString();
                String verifyCode = etVerification.getText().toString();
                password = etPassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, R.string.regist_error_tips, Toast.LENGTH_SHORT).show();
                    return;
                }

                RxUtils.regist(user, null, password, verifyCode, new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        registSuccess();
                    }
                });
                break;
        }
    }

    private void gotVerificationCodeSuccess() {
        tvGotVerify.setEnabled(false);
        mainHandler.postDelayed(updateVerificationCodeTextView, 1000);
    }

    private void gotVerificationCodeFail() {
        Toast.makeText(this, R.string.got_verification_code_failed, Toast.LENGTH_SHORT).show();
    }

    private void registSuccess() {
        Toast.makeText(this, R.string.regist_success, Toast.LENGTH_SHORT).show();
        //should login here and turn to main page.
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

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mainHandler != null) {
            updateCount = -1;
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler = null;
        }
    }
}
