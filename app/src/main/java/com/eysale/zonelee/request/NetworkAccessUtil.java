package com.eysale.zonelee.request;

import com.eysale.zonelee.response.BaseResponse;
import com.eysale.zonelee.response.LoginResponse;
import com.eysale.zonelee.response.RegistLoginUserResponse;
import com.eysale.zonelee.util.EncryptionUtil;
import com.eysale.zonelee.util.LogPrinter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkAccessUtil {

    private static NetworkAccessUtil instance;
    private final String baseUrl = "https://www.eysale.com/";
    private OkHttpClient mClient;

    private NetworkAccessUtil() {}

    static NetworkAccessUtil getInstance() {
        if (instance == null){
            synchronized(NetworkAccessUtil.class){
                if (instance == null)
                    instance = new NetworkAccessUtil();
                }
            }
        return instance;
    }

    private void checkClient() {
        if(mClient == null) {
            mClient = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .build();
        }
    }

    private Retrofit getBaseRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .baseUrl(baseUrl).build();
    }

    BaseResponse startGetVerificationCode(String email) throws IOException {
        checkClient();

        Retrofit retrofit = getBaseRetrofit();

        RetrofitInterface registInterface =  retrofit.create(RetrofitInterface.class);
        Call<BaseResponse> registerResponseCall = registInterface.registGotVerificationCode(email);
        retrofit2.Response<BaseResponse> execute = registerResponseCall.execute();
        return execute.body();
    }

    RegistLoginUserResponse registUser(String email, String tel, String password, String verifyCode) throws IOException {
        checkClient();

        Retrofit retrofit = getBaseRetrofit();

        RetrofitInterface registInterface =  retrofit.create(RetrofitInterface.class);

        String s = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<RegistLoginUserResponse> registRep = registInterface.registUser(email, tel, s, verifyCode);
        retrofit2.Response<RegistLoginUserResponse> response = registRep.execute();
        LogPrinter.i("ttt", "  " + response.body());
        return response.body();
    }

    LoginResponse userLogin(String user, String password) throws IOException {
        checkClient();

        Retrofit retrofit = getBaseRetrofit();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        password = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<LoginResponse> userCall = retrofitInterface.userLogin(user, password);
        Response<LoginResponse> execute = userCall.execute();
        LogPrinter.i("ttt", "  " + execute.body());
        return execute.body();
    }

    RegistLoginUserResponse userLogin1(String user, String password) throws IOException {
        checkClient();
        Retrofit retrofit = getBaseRetrofit();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        password = EncryptionUtil.encryptionByMD5("zhiji" + password);
        Call<ResponseBody> userCall = retrofitInterface.userLogin1(user, password);
        Response<ResponseBody> execute = userCall.execute();
        LogPrinter.i("ttt", "  " + execute.body().string());
        return null;
    }

    BaseResponse userLoginOut(String userId) throws IOException {
        checkClient();
        Retrofit retrofit = getBaseRetrofit();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<BaseResponse> call = retrofitInterface.userLoginOut(userId);
        Response<BaseResponse> execute = call.execute();
        return execute.body();
    }


}
