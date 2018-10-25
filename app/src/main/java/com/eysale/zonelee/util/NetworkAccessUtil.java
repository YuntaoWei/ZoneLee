package com.eysale.zonelee.util;

import com.eysale.zonelee.request.RetrofitInterface;
import com.eysale.zonelee.response.RegisterResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAccessUtil {

    private static NetworkAccessUtil instance;
    private final String baseUrl = "http://www.sysale.com";
    private OkHttpClient mClient;

    private NetworkAccessUtil() {}

    public static NetworkAccessUtil getInstance() {
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

    public RegisterResponse startGetVerificationCode(String email) throws IOException {
        checkClient();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .baseUrl(baseUrl).build();

        RetrofitInterface registInterface =  retrofit.create(RetrofitInterface.class);
        Call<RegisterResponse> registRep = registInterface.registGotVerificationCode(email);
        retrofit2.Response<RegisterResponse> response = registRep.execute();

        return response.body();
    }

    public ResponseBody registUser(String name, String password, String verifyCode) throws IOException {
        checkClient();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .baseUrl(baseUrl).build();

        RetrofitInterface registInterface =  retrofit.create(RetrofitInterface.class);
        Call<ResponseBody> registRep = registInterface.registUser(name, password, verifyCode);
        retrofit2.Response<ResponseBody> response = registRep.execute();

        return response.body();
    }


}
