package com.eysale.zonelee.bean;

public class FoundPageData {

    public String title;
    public String author;

    public FoundPageData() {}

    public FoundPageData(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "FoundPageData{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
