package com.eysale.zonelee.http.request;

import com.eysale.zonelee.http.response.BaseResponse;
import com.eysale.zonelee.http.response.LoginResponse;
import com.eysale.zonelee.http.response.RegistLoginUserResponse;
import com.eysale.zonelee.util.LogPrinter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private static final String TAG = "RxUtils";

    /**
     * 用户登陆接口。
     * @param name 用户名
     * @param password 密码
     * @param callBack 登陆结果回调
     */
    public static void login(final String name, final String password, Consumer<LoginResponse> callBack) {
        Observable.create(new ObservableOnSubscribe<LoginResponse>() {
            @Override
            public void subscribe(ObservableEmitter<LoginResponse> emitter) throws Exception {
                LoginResponse user = UserModelUtil.getInstance().userLogin(name, password);
                LogPrinter.i("ttt", "LoginResponse : " + user);
                emitter.onNext(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(callBack);

    }

    /**
     * 注册接口，注册一个新的账户
     * @param email 邮箱，作为登陆账户，也可以作为找回密码的凭证
     * @param tel 电话号码，保留接口，作用同邮箱
     * @param password 账户密码
     * @param verifyCode 验证码
     * @param subscribe 注册结果回调
     */
    public static void regist(final String email, final String tel, final String password, final String verifyCode, Observer subscribe) {
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                RegistLoginUserResponse rb = UserModelUtil.getInstance().registUser(email, tel, password, verifyCode);
                if (rb == null || !rb.code.equals("success")) {
                    emitter.onError(new Throwable(rb == null ? "" : rb.message));
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(subscribe);
    }

    /**
     * 获取验证码，以邮箱的形式
     * @param email 接收验证码的邮箱
     * @param success 验证码发送成功的回调
     * @param error 发送失败的回调
     */
    public static void getRegistVerificationCode(final String email, Consumer<BaseResponse> success, Consumer<Throwable> error) {
        Observable ob = Observable.create(new ObservableOnSubscribe<BaseResponse>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse> emitter) throws Exception {
                BaseResponse rp = UserModelUtil.getInstance().startGetVerificationCode(email);
                LogPrinter.d(TAG, "getRegistVerificationCode result : " + rp);
                if (null == rp) {
                    emitter.onError(new Throwable("Server or network error!"));
                }

                if (rp.code.equals("success")) {
                    emitter.onNext(rp);
                } else if (rp.code.equals("failed")) {
                    emitter.onError(new Throwable(rp.message));
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());

        ob.subscribe(success, error);
    }

    /**
     * 用户登出接口
     * @param userId 用户ID，登陆完成后由服务器返回的一个特殊token
     * @param callBack 登出结果回调
     */
    public static void loginOut(final String userId, final Consumer<BaseResponse> callBack) {
        Observable.create(new ObservableOnSubscribe<BaseResponse>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse> emitter) throws Exception {
                BaseResponse response = UserModelUtil.getInstance().userLoginOut(userId);
                callBack.accept(response);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(callBack);
    }

    public static void execute(ObservableOnSubscribe observableOnSubscribe, Observer ob) {
        Observable.create(observableOnSubscribe)
                .subscribe(ob);
    }

}
