package com.eysale.zonelee.http.response;

public class LoginResponse extends BaseResponse {

    public User content;


    public LoginResponse(String code, String message, User content) {
        super(code, message);
        this.content = content;
    }

    public User getContent() {
        return content;
    }

    public void setContent(User content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "content=" + content +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
