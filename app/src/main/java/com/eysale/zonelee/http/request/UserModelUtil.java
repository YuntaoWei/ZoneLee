package com.eysale.zonelee.http.request;

import com.eysale.zonelee.http.RetrofitManager;
import com.eysale.zonelee.http.response.BaseResponse;
import com.eysale.zonelee.http.response.LoginResponse;
import com.eysale.zonelee.http.response.RegistLoginUserResponse;
import com.eysale.zonelee.util.EncryptionUtil;
import com.eysale.zonelee.util.LogPrinter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 用户模块，用于用户的注册，登入，登出等账户相关操作。
 */
public class UserModelUtil {
    private static UserModelUtil instance;

    private UserModelUtil() {}

    public static UserModelUtil getInstance() {
        if (instance == null){
            synchronized(UserModelUtil.class){
                if (instance == null)
                    instance = new UserModelUtil();
            }
        }
        return instance;
    }

    public BaseResponse startGetVerificationCode(String email) throws IOException {
        UserModelAPI userModelAPI = RetrofitManager.getInstance().getUserModelAPI();
        Call<BaseResponse> registerResponseCall = userModelAPI.registGotVerificationCode(email);
        retrofit2.Response<BaseResponse> execute = registerResponseCall.execute();
        return execute.body();
    }

    public RegistLoginUserResponse registUser(String email, String tel, String password, String verifyCode) throws IOException {
        UserModelAPI userModelAPI =  RetrofitManager.getInstance().getUserModelAPI();
        String s = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<RegistLoginUserResponse> registRep = userModelAPI.registUser(email, tel, s, verifyCode);
        retrofit2.Response<RegistLoginUserResponse> response = registRep.execute();
        LogPrinter.i("ttt", "  " + response.body());
        return response.body();
    }

    public LoginResponse userLogin(String user, String password) throws IOException {
        UserModelAPI userModelAPI =  RetrofitManager.getInstance().getUserModelAPI();
        password = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<LoginResponse> userCall = userModelAPI.userLogin(user, password);
        Response<LoginResponse> execute = userCall.execute();
        LogPrinter.i("ttt", "  " + execute.body());
        return execute.body();
    }

    public RegistLoginUserResponse userLogin1(String user, String password) throws IOException {
        UserModelAPI userModelAPI =  RetrofitManager.getInstance().getUserModelAPI();
        password = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<ResponseBody> userCall = userModelAPI.userLogin1(user, password);
        Response<ResponseBody> execute = userCall.execute();
        LogPrinter.i("ttt", "  " + execute.body().string());
        return null;
    }

    public BaseResponse userLoginOut(String userId) throws IOException {
        UserModelAPI userModelAPI =  RetrofitManager.getInstance().getUserModelAPI();
        Call<BaseResponse> call = userModelAPI.userLoginOut(userId);
        Response<BaseResponse> execute = call.execute();
        return execute.body();
    }
}
