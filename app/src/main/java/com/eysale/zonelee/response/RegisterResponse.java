package com.eysale.zonelee.response;

public class RegisterResponse {

    public String state;
    public String message;

    public RegisterResponse() {}

    public RegisterResponse(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
