package com.eysale.zonelee.util;

import com.eysale.zonelee.request.RetrofitInterface;
import com.eysale.zonelee.response.RegisterResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

    private static NetUtils instance;
    private final String baseUrl = "http://www.sysale.com";

    private NetUtils() {}

    public static NetUtils getInstance() {
        if (instance == null){
            synchronized(NetUtils.class){
                if (instance == null)
                    instance = new NetUtils();
                }
            }
        return instance;
    }

    public RegisterResponse startGetVerificationCode(String email) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseUrl).build();

        RetrofitInterface registInterface =  retrofit.create(RetrofitInterface.class);
        Call<RegisterResponse> registRep = registInterface.registGotVerificationCode(email);
        retrofit2.Response<RegisterResponse> response = registRep.execute();

        return response.body();
    }


}
