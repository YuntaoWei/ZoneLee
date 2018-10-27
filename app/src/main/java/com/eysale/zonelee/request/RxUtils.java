package com.eysale.zonelee.request;

import android.annotation.SuppressLint;

import com.eysale.zonelee.response.BaseResponse;
import com.eysale.zonelee.response.LoginResponse;
import com.eysale.zonelee.response.RegistLoginUserResponse;
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

    public static void login(final String name, final String password, Consumer<LoginResponse> callBack) {
        Observable.create(new ObservableOnSubscribe<LoginResponse>() {
            @Override
            public void subscribe(ObservableEmitter<LoginResponse> emitter) throws Exception {
                LoginResponse user = NetworkAccessUtil.getInstance().userLogin(name, password);
                LogPrinter.i("ttt", "LoginResponse : " + user);
                emitter.onNext(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(callBack);

    }

    public static void regist(final String email, final String tel, final String password, final String verifyCode, Observer subscribe) {
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                RegistLoginUserResponse rb = NetworkAccessUtil.getInstance().registUser(email, tel, password, verifyCode);
                if (rb == null || !rb.code.equals("success")) {
                    emitter.onError(new Throwable(rb == null ? "" : rb.message));
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(subscribe);
    }

    public static void getRegistVerificationCode(final String email, Consumer<BaseResponse> success, Consumer<Throwable> error) {
        Observable ob = Observable.create(new ObservableOnSubscribe<BaseResponse>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse> emitter) throws Exception {
                BaseResponse rp = NetworkAccessUtil.getInstance().startGetVerificationCode(email);
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

    public static void loginOut(final String userId, final Consumer<BaseResponse> callBack) {
        Observable.create(new ObservableOnSubscribe<BaseResponse>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse> emitter) throws Exception {
                BaseResponse response = NetworkAccessUtil.getInstance().userLoginOut(userId);
                callBack.accept(response);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(callBack);
    }

}
