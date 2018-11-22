package com.eysale.zonelee.util;

public class TagUtil {
    public static final String PUBLISH_TAG = "tag";
    public static final int BASE_TAG_MUSIC = 1;
    public static final int BASE_TAG_MOVIE = 2;
    public static final int BASE_TAG_NOVEL = 3;

    public static String getBaseTagString(int tag) {
        return tag == BASE_TAG_MOVIE ? "电影" : tag == BASE_TAG_MUSIC ? "音乐" : "文学";
    }

}
