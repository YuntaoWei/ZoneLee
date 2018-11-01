package com.eysale.zonelee.bean;

import java.util.List;

public class FoundDatas implements BaseData {
    private List<FoundPageDetailData> datas;

    public FoundDatas(List<FoundPageDetailData> datas) {
        this.datas = datas;
    }

    public List<FoundPageDetailData> getDatas() {
        return datas;
    }

    public void setDatas(List<FoundPageDetailData> datas) {
        this.datas = datas;
    }
}
