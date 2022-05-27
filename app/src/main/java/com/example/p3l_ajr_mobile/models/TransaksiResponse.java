package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiResponse {

    private String message;
    @SerializedName("data")
    private List<Transaksi> transaksiList;

    public TransaksiResponse(String message,List<Transaksi> transaksiList) {
        this.message = message;
        this.transaksiList = transaksiList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }
}
