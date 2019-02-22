package com.eysale.zonelee.http.request;

import com.eysale.zonelee.http.response.BaseResponse;
import com.eysale.zonelee.http.response.LoginResponse;
import com.eysale.zonelee.http.response.RegistLoginUserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserModelAPI {

    /**
     * 邮箱获取验证码
     * @param email 接收验证码的邮箱
     * @return 响应数据，{@link com.eysale.zonelee.http.response.BaseResponse}
     */
    @GET(URLManager.URL_USER_SEND_EMAIL)
    Call<BaseResponse> registGotVerificationCode(@Query("email") String email);

    /**
     * 用户注册。
     * @param email 邮箱，用于账户登录。
     * @param tel 手机号，也可以用于登录。
     * @param password 账户密码。
     * @param safeCode 验证码。
     * @return 响应数据。{@link com.eysale.zonelee.http.response.RegistLoginUserResponse}
     */
    @GET(URLManager.URL_USER_REGISTER)
    Call<RegistLoginUserResponse> registUser(@Query("email") String email, @Query("tel") String tel,
                                             @Query("password") String password, @Query("safeCode") String safeCode);

    /**
     * 用户登录。
     * @param username 用户名。
     * @param password 用户密码。
     * @return 响应数据。{@link com.eysale.zonelee.http.response.LoginResponse}
     */
    @GET(URLManager.URL_USER_LOGIN)
    Call<LoginResponse> userLogin(@Query("username") String username, @Query("password") String password);

    /**
     * 用户登录
     * @param username 用户名。
     * @param password 用户密码。
     * @return
     */
    @GET(URLManager.URL_USER_LOGIN)
    Call<ResponseBody> userLogin1(@Query("username") String username, @Query("password") String password);

    /**
     * 退出登录。
     * @param userId 登录后从服务器返回的token。
     * @return 响应数据。{@link com.eysale.zonelee.http.response.BaseResponse}
     */
    @GET(URLManager.URL_USER_LOGIN_OUT)
    Call<BaseResponse> userLoginOut(@Query("userId") String userId);
}
