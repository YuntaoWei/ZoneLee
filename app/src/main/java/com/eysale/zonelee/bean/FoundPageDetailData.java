package com.eysale.zonelee.bean;

public class FoundPageDetailData extends FoundPageData {

    public String detail;
    public String time;

    public FoundPageDetailData() {
    }

    public FoundPageDetailData(String title, String author, String detail, String time) {
        super(title, author);
        this.detail = detail;
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "FoundPageDetailData{" +
                "detail='" + detail + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
