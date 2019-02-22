package com.eysale.zonelee.http.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager instance;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;

    private RetrofitManager() {
        createBaseRetrofit();
    }

    public static RetrofitManager getInstance() {
        if (instance == null){
            synchronized(RetrofitManager.class){
                if (instance == null)
                    instance = new RetrofitManager();
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

    private void createBaseRetrofit() {
        checkClient();
        if(mRetrofit == null)
            mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .baseUrl(Config.BASE_URL).build();
    }

    public UserModelAPI getNetOperationImpl() {
        createBaseRetrofit();
        return mRetrofit.create(UserModelAPI.class);
    }

}
