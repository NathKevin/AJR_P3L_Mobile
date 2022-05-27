package com.example.p3l_ajr_mobile.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoResponse {

    private String message;
    private Promo promo;
    private String day;
    private String date;
    @SerializedName("data")
    private List<Promo> promoList;

    public PromoResponse(String message, Promo promo, String day, String date) {
        this.message = message;
        this.promo = promo;
        this.day = day;
        this.date = date;
    }

    public PromoResponse(List<Promo> promoList) {
        this.promoList = promoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Promo> getPromoList() {
        return promoList;
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
    }
}
