package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    private String message;
    private User user;
    @SerializedName("token")
    private String api_token;

    public UserResponse(String message, User user, String api_token) {
        this.message = message;
        this.user = user;
        this.api_token = api_token;
    }

    public String getApi_token() {
        return api_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
