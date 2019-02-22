package com.eysale.zonelee.http.request;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ArticleModeApi {

    /**
     * 上传图片，主要是用于上传文章中夹带的图片。
     * @return 响应j结果。
     */
    @POST(URLManager.URL_UPLOAD_ARTICLE_PICTURE)
    Observable<String> uploadPicture(@PartMap Map<String, RequestBody> params);

    /**
     * 用户上传一篇新文章
     * @param title 文章标题
     * @param content 文章内容
     * @param type 文章类型
     * @param typeName 文章类型名
     * @param authority 文章作者
     * @return
     */
    @POST(URLManager.URL_UPLOAD_ARTICLE)
    Call<String> createArticle(String title, String content, String type, String typeName, String authority);

}
