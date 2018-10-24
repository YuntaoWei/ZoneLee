package com.eysale.zonelee.util;

import android.util.Log;

import com.eysale.zonelee.response.RegisterResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private static final String TAG = "RxUtils";

    public static void login(String name, String password, Consumer<String> callBack) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                Log.i("ttt", "Consumer accept!");
            }
        });
    }

    public static void getRegistVerificationCode(final String email, Consumer<RegisterResponse> callBack) {
        Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterResponse> emitter) throws Exception {
                RegisterResponse rp = NetUtils.getInstance().startGetVerificationCode(email);
                LogPrinter.i(TAG, "getRegistVerificationCode result : " + rp);
            }
        }).observeOn(Schedulers.newThread())
          .subscribeOn(AndroidSchedulers.mainThread())
          .subscribe(callBack);
    }

}
