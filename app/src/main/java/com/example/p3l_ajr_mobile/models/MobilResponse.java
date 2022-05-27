package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilResponse {

    private String message;
    private Mobil mobil;
    @SerializedName("data")
    private List<Mobil> mobilList;

    public MobilResponse(String message, Mobil mobil) {
        this.message = message;
        this.mobil = mobil;
    }

    public MobilResponse(List<Mobil> mobilList) {
        this.mobilList = mobilList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public List<Mobil> getMobilList() {
        return mobilList;
    }

    public void setMobilList(List<Mobil> mobilList) {
        this.mobilList = mobilList;
    }
}
