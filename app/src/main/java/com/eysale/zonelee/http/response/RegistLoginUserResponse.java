package com.eysale.zonelee.http.response;

public class RegistLoginUserResponse extends BaseResponse {

    public String content;

    public RegistLoginUserResponse(String code, String message, String content) {
        super(code, message);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RegistUserResponse{" +
                "content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
