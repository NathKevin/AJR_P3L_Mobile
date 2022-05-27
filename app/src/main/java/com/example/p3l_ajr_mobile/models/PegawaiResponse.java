package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

public class PegawaiResponse {

    private String message;
    @SerializedName("user")
    private Pegawai pegawai;
    @SerializedName("token")
    private String api_token;

    public PegawaiResponse(String message, Pegawai pegawai, String api_token) {
        this.message = message;
        this.pegawai = pegawai;
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

    public Pegawai getPegawai(){
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }
}
