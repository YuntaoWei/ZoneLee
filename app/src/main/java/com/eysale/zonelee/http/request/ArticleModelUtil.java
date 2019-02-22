package com.eysale.zonelee.http.request;

import okhttp3.OkHttpClient;

/**
 * 用于文章编写发布，查看，拉取，收藏等一系列文章操作相关的接口
 */
public class ArticleModelUtil {

    private static ArticleModelUtil instance;
    private OkHttpClient mClient;

    private ArticleModelUtil() {}

    public static ArticleModelUtil getInstance() {
        if (instance == null){
            synchronized(ArticleModelUtil.class){
                if (instance == null)
                    instance = new ArticleModelUtil();
            }
        }
        return instance;
    }

}
