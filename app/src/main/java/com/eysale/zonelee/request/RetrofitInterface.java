package com.eysale.zonelee.request;

import com.eysale.zonelee.response.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("user/sendCode")
    Call<RegisterResponse> registGotVerificationCode(@Query("email") String email);

    @GET("user/regist")
    Call<ResponseBody> registUser(@Query("userName") String userName, @Query("password") String password, @Query("verifyCode") String verifyCode);

}
