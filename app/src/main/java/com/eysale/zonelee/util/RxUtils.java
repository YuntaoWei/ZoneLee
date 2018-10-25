package com.eysale.zonelee.util;

import android.util.Log;

import com.eysale.zonelee.response.RegisterResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RxUtils {

    private static final String TAG = "RxUtils";

    public static void login(String name, String password, Consumer<String> callBack) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread());

    }

    public static void regist(final String name, final String password, final String verifyCode, Observer subscribe) {
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                ResponseBody rb = NetworkAccessUtil.getInstance().registUser(name, password, verifyCode);
                if(rb == null) {
                    emitter.onError(new Throwable("Regist error!"));
                }

                emitter.onComplete();
            }
        }).observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

    public static void getRegistVerificationCode(final String email, Consumer<RegisterResponse> success, Consumer<Throwable> error) {
        Observable ob = Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterResponse> emitter) throws Exception {
                RegisterResponse rp = NetworkAccessUtil.getInstance().startGetVerificationCode(email);
                LogPrinter.i(TAG, "getRegistVerificationCode result : " + rp);
                if(null == rp) {
                    emitter.onError(new Throwable("Server or network error!"));
                }

                if(rp.state.equals("success")) {
                    emitter.onNext(rp);
                } else if(rp.state.equals("failed")) {
                    emitter.onError(new Throwable(rp.message));
                }
            }
        }).observeOn(Schedulers.newThread())
          .subscribeOn(AndroidSchedulers.mainThread());

        ob.subscribe(success, error);
    }

}
