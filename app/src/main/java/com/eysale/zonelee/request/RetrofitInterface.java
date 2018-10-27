package com.eysale.zonelee.request;

import com.eysale.zonelee.response.BaseResponse;
import com.eysale.zonelee.response.LoginResponse;
import com.eysale.zonelee.response.RegistLoginUserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("user/sendMail")
    Call<BaseResponse> registGotVerificationCode(@Query("email") String email);

    @GET("user/register")
    Call<RegistLoginUserResponse> registUser(@Query("email") String email, @Query("tel") String tel,
                                             @Query("password") String password, @Query("safeCode") String safeCode);

    @GET("user/login")
    Call<LoginResponse> userLogin(@Query("username") String username, @Query("password") String password);

    @GET("user/login")
    Call<ResponseBody> userLogin1(@Query("username") String username, @Query("password") String password);

    @GET("user/loginout")
    Call<BaseResponse> userLoginOut(@Query("userId") String userId);

}
