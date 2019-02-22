package com.eysale.zonelee.http;

import com.eysale.zonelee.http.request.Config;
import com.eysale.zonelee.http.request.UserModelAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager<T> {

    private static RetrofitManager INSTANCE;

    private OkHttpClient mClient;
    private Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        if(INSTANCE == null) {
            synchronized (RetrofitManager.class) {
                if(INSTANCE == null) {
                    INSTANCE = new RetrofitManager();
                }
            }
        }

        return INSTANCE;
    }

    private RetrofitManager() {}

    private Retrofit getBaseRetrofit() {
        checkClient();
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mClient)
                    .baseUrl(Config.BASE_URL).build();
        }
        return mRetrofit;
    }

    private void checkClient() {
        if(mClient == null) {
            mClient = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
    }

    public UserModelAPI getUserModelAPI() {
        return getBaseRetrofit().create(UserModelAPI.class);
    }

    public T getApi(Class<T> cls) {
        return getBaseRetrofit().create(cls);
    }

}
