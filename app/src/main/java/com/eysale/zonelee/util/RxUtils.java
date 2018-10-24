package com.eysale.zonelee.util;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

public class RxUtils {

    public static void login(String name, String password, Consumer<Integer> callBack) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i("ttt", "ObservableOnSubscribe subscribe!");
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("ttt", "Consumer accept!");
            }
        });
    }

}
