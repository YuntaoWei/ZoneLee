package com.eysale.zonelee.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eysale.zonelee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
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
        switch (v.getId()) {
            case R.id.tv_verification:

                break;

            case R.id.bt_regist_login:
                String phoneNumber = etPhoneNumber.getText().toString();
                String verifyCode = etVerification.getText().toString();
                String password = etPassword.getText().toString();
                if(TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, R.string.regist_error_tips, Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
        }
    }
}
