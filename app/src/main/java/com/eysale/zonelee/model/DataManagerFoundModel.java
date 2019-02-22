package com.eysale.zonelee.model;

import com.eysale.zonelee.bean.FoundDatas;
import com.eysale.zonelee.http.request.RxUtils;
import com.eysale.zonelee.util.LogPrinter;
import com.eysale.zonelee.util.NetWorkUtils;

import java.util.concurrent.Semaphore;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DataManagerFoundModel {

    private static final String TAG = "DataManagerFoundModel";
    private static DataManagerFoundModel mInstance;
    private LoadCallBack callBack;
    private DataLoader mLoader;

    private Semaphore mSemaphore;

    private DataManagerFoundModel() {
    }

    private DataManagerFoundModel(LoadCallBack c) {
        mLoader = new DataLoader();
        callBack = c;
    }

    public static DataManagerFoundModel getInstance() {
        if(mInstance == null)
            mInstance = new DataManagerFoundModel();

        return mInstance;
    }

    public static DataManagerFoundModel getInstance(LoadCallBack c) {
        if(mInstance == null)
            mInstance = new DataManagerFoundModel();

        return mInstance;
    }

    public void addLoadCallback(LoadCallBack c) {
        callBack = c;
    }

    public void start() {
        LogPrinter.i(TAG, "DataManagerFoundModel start");
        if(mLoader == null) {
            mLoader = new DataLoader();
            mLoader.start();
        }

        nextPage();
    }

    public void nextPage() {
        if(mSemaphore == null) {
            mSemaphore = new Semaphore(0);
        }
        mSemaphore.release();
    }

    public void stop() {
        if(mLoader != null) {
            mLoader.stopTask();
            mLoader = null;
            mSemaphore.release();
        }
    }

    private class DataLoader extends Thread implements Observer<FoundDatas> {

        boolean isRunning = true;
        ObservableOnSubscribe loadTask;

        public DataLoader() {
            loadTask = new ObservableOnSubscribe<FoundDatas>() {
                @Override
                public void subscribe(ObservableEmitter<FoundDatas> emitter) throws Exception {
                    LogPrinter.i(TAG, "load task subscribe : " + Thread.currentThread().getName());
                    if(callBack != null)
                        callBack.onLoadStart();

                    emitter.onNext(new FoundDatas(NetWorkUtils.getTestData(20)));
                    emitter.onComplete();
                }
            };
        }

        public void stopTask() {
            isRunning = false;
        }

        @Override
        public void run() {
            LogPrinter.i(TAG, "DataLoader run--------------");
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            while(isRunning) {
                if(mSemaphore == null)
                    continue;
                try {
                    mSemaphore.acquire();
                    if(isRunning) {
                        RxUtils.execute(loadTask, this);
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(FoundDatas o) {
            LogPrinter.i(TAG, "Observer onNext : " + Thread.currentThread().getName() + "   " + o);
            if(callBack != null) {
                callBack.onLoadComplete(o);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            LogPrinter.i(TAG, "Observer onComplete : " + Thread.currentThread().getName());

        }
    }

}
