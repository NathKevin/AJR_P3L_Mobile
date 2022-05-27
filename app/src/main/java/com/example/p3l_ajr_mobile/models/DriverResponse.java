package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

public class DriverResponse {

    private String message;
    @SerializedName("user")
    private Driver driver;
    @SerializedName("token")
    private String api_token;
    @SerializedName("data")
    private Driver driver2;

    public DriverResponse(String message, Driver driver, String api_token) {
        this.message = message;
        this.driver = driver;
        this.api_token = api_token;
    }

    public DriverResponse(String message, Driver driver) {
        this.message = message;
        this.driver2 = driver;
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

    public Driver getDriver(){
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver2() {
        return driver2;
    }

    public void setDriver2(Driver driver2) {
        this.driver2 = driver2;
    }
}
