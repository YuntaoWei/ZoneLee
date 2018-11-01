package com.eysale.zonelee.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.eysale.zonelee.bean.FoundPageDetailData;

import java.util.ArrayList;
import java.util.List;

public class NetWorkUtils {

    public static boolean canAccessNetwork(Context ctx) {
        ConnectivityManager manager = (ConnectivityManager) ctx .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }

    public static List<FoundPageDetailData> getTestData(int count) {
        List<FoundPageDetailData> datas = new ArrayList<>(count);
        for (int i = 0; i < count; i ++) {
            datas.add(
                    new FoundPageDetailData(
                            "程序员刚写完代码，就被开除了，网友：你TM真是个天才",
                            "Java高级架构" ,
                            "https://www.jianshu.com/p/d9c07b130f0d",
                            "2018-11-1"));
        }
        return datas;
    }

}
