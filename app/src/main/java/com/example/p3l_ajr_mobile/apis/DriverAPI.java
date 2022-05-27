package com.example.p3l_ajr_mobile.apis;

public class DriverAPI {
    public static String BASE_URL = "http://192.168.1.8:8000/api/";
    public static String BASE_URL_PICTURE = "http://192.168.1.8:8000/";

    public static final String UPDATE_RATE_URL = BASE_URL + "updateRatingDriver/driver/";
    public static final String UPDATE_PROFIL = BASE_URL + "updateProfile/driver/";
    public static final String UPDATE_STATUS = BASE_URL + "updateStatusAktif/driver/";
    public static final String UPDATE_EMAIL = BASE_URL + "updateEmail/driver/";
    public static final String UPDATE_PASS = BASE_URL + "updatePassword/driver/";
    public static final String GET_DRIVER = BASE_URL + "show/driver/";
    public static final String GET_PICTURE = BASE_URL_PICTURE + "storage/";
}
