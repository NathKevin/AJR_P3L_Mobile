package com.example.p3l_ajr_mobile.apis;

public class TransaksiAPI {
    public static String BASE_URL = "http://192.168.1.8:8000/api/";
    public static String BASE_URL_PICTURE = "http://192.168.1.8:8000/";

    public static final String GET_ALL = BASE_URL + "showLengkapByCustomer/transaksi/";
    public static final String GET_ALL_BY_DRIVER = BASE_URL + "showLengkapByDriver/transaksi/";
    public static final String UPDATE_RATE_PERFORMA_TRANSAKSI = BASE_URL + "updateRate/transaksi/";
    public static final String GET_PICTURE = BASE_URL_PICTURE + "storage/";
}
