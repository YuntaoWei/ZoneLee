package com.eysale.zonelee.model;

import com.eysale.zonelee.bean.BaseData;

public interface LoadCallBack<T extends BaseData> {

    public void onLoadStart();

    public void onLoadComplete(T data);

}
