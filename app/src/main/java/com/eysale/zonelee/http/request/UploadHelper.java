package com.eysale.zonelee.http.request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadHelper {

    private Map<String, RequestBody> requestParams;
    private UploadHelper() {
        requestParams = new HashMap<>();
    }

    public static class UploadHelperHolder {
        public static UploadHelper INSTANCE = new UploadHelper();
    }

    public UploadHelper addRequestParameter(String key, String value) {
        RequestBody rb = RequestBody.create(MediaType.get("text/plain;charset=UTF-8"), value);
        requestParams.put(key, rb);
        return this;
    }

    public UploadHelper addPostParameterFile(String key, File file) {
        RequestBody rb = RequestBody.create(MediaType.get("multipart/form-data;charset=UTF-8"), file);
        requestParams.put(key, rb);
        return this;
    }

    public Map<String, RequestBody> build() {
        return requestParams;
    }

    public UploadHelper reset() {
        requestParams.clear();
        return this;
    }

}
