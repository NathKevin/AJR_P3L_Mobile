package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanResponse {

    private String message;
    private Laporan laporan;
    @SerializedName("data")
    private List<Laporan> laporanList;

    public LaporanResponse(String message, List<Laporan> laporanList) {
        this.message = message;
        this.laporanList = laporanList;
    }

    public LaporanResponse(String message, Laporan laporan) {
        this.message = message;
        this.laporan = laporan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Laporan getLaporan() {
        return laporan;
    }

    public void setLaporan(Laporan laporan) {
        this.laporan = laporan;
    }

    public List<Laporan> getLaporanList() {
        return laporanList;
    }

    public void setLaporanList(List<Laporan> laporanList) {
        this.laporanList = laporanList;
    }
}
