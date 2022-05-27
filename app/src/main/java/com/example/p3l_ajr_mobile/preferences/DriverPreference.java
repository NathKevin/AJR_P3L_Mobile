package com.example.p3l_ajr_mobile.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.p3l_ajr_mobile.models.Driver;

public class DriverPreference {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //    Mendefinisikan Key
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "idDriver";
    public static final String KEY_NAMA = "namaDriver";
    public static final String KEY_ALAMAT = "alamatDriver";
    public static final String KEY_TL = "tanggalLahirDriver";
    public static final String KEY_KELAMIN = "jenisKelaminDriver";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TELP = "noTelpDriver";
    public static final String KEY_BAHASA = "bahasa";
    public static final String KEY_STATUS = "statusKetersediaanDriver";
    public static final String KEY_HARGA = "hargaSewaDriver";
    public static final String KEY_RERATA = "rerataRating";
    public static final String KEY_FOTO = "fotoDriver";
    public static final String KEY_SIM = "fotocopySIM";
    public static final String KEY_NAPZA = "bebasNAPZA";
    public static final String KEY_JIWA = "kesehatanJiwa";
    public static final String KEY_JASMANI = "kesehatanJasmani";
    public static final String KEY_SKCK = "SKCK";
    public static final String KEY_AKTIF = "isActive";
    public static final String KEY_API = "api_token";
    public static final String KEY_BERKAS = "statusBerkas";

    public DriverPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("driverPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String idDriver, String namaDriver, String alamatDriver, String tanggalLahirDriver, String jenisKelaminDriver,
                         String email, String password, String noTelpDriver, String bahasa, Integer statusKetersediaanDriver, Double hargaSewaDriver,
                         Double rerataRating, String fotoDriver, String fotocopySIM, String bebasNAPZA, String kesehatanJiwa, String kesehatanJasmani,
                         String SKCK, Integer isActive, String api_token, String statusBerkas){
        // Menyimpan data login ke sharedPreferences dengan key dan value
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, idDriver);
        editor.putString(KEY_NAMA, namaDriver);
        editor.putString(KEY_ALAMAT, alamatDriver);
        editor.putString(KEY_TL, tanggalLahirDriver);
        editor.putString(KEY_KELAMIN, jenisKelaminDriver);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_TELP, noTelpDriver);
        editor.putString(KEY_BAHASA, bahasa);
        editor.putString(KEY_STATUS, statusKetersediaanDriver.toString());
        editor.putString(KEY_HARGA, hargaSewaDriver.toString());
        editor.putString(KEY_RERATA, rerataRating.toString());
        editor.putString(KEY_FOTO, fotoDriver);
        editor.putString(KEY_SIM, fotocopySIM);
        editor.putString(KEY_NAPZA, bebasNAPZA);
        editor.putString(KEY_JIWA, kesehatanJiwa);
        editor.putString(KEY_JASMANI, kesehatanJasmani);
        editor.putString(KEY_SKCK, SKCK);
        editor.putString(KEY_AKTIF, isActive.toString());
        editor.putString(KEY_API, api_token);
        editor.putString(KEY_BERKAS, statusBerkas);

        // Jangan lupa commit karena kalo hanyan set editornya saja tidak commit akan sia-sia
        editor.commit();
    }

    public void setUpdateDriver(String namaDriver, String alamatDriver, String tanggalLahirDriver, String jenisKelaminDriver, String noTelpDriver, String bahasa){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAMA, namaDriver);
        editor.putString(KEY_ALAMAT, alamatDriver);
        editor.putString(KEY_TL, tanggalLahirDriver);
        editor.putString(KEY_KELAMIN, jenisKelaminDriver);
        editor.putString(KEY_TELP, noTelpDriver);
        editor.putString(KEY_BAHASA, bahasa);

        editor.commit();
    }

    public void setUpdateStatus(Integer isActive){
        editor.putString(KEY_AKTIF, isActive.toString());

        editor.commit();
    }

    public void setUpdateEmail(String email){
        editor.putString(KEY_EMAIL, email);

        editor.commit();
    }

    public void setUpdatePassword(String password){
        editor.putString(KEY_PASSWORD, password);

        editor.commit();
    }

    public Driver getDriverlogin(){
        // Mengembalikan object User untuk menampilkan data user jika user sudah login
        String idDriver;
        String namaDriver;
        String alamatDriver;
        String tanggalLahirDriver;
        String jenisKelaminDriver;
        String email;
        String password;
        String noTelpDriver;
        String bahasa;
        Integer statusKetersediaanDriver;
        Double hargaSewaDriver;
        Double rerataRating;
        String fotoDriver;
        String fotocopySIM;
        String bebasNAPZA;
        String kesehatanJiwa;
        String kesehatanJasmani;
        String SKCK;
        Integer isActive;
        String api_token;
        String statusBerkas;
        String temp;

        idDriver = sharedPreferences.getString(KEY_ID, null);
        namaDriver = sharedPreferences.getString(KEY_NAMA, null);
        alamatDriver = sharedPreferences.getString(KEY_ALAMAT, null);
        tanggalLahirDriver = sharedPreferences.getString(KEY_TL, null);
        jenisKelaminDriver = sharedPreferences.getString(KEY_KELAMIN, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        noTelpDriver = sharedPreferences.getString(KEY_TELP, null);
        bahasa = sharedPreferences.getString(KEY_BAHASA, null);

        temp = sharedPreferences.getString(KEY_STATUS, null);
        if(temp != null){
            statusKetersediaanDriver = Integer.parseInt(temp);
        }else{
            statusKetersediaanDriver = null;
        }
        temp = sharedPreferences.getString(KEY_HARGA, null);
        if(temp != null){
            hargaSewaDriver = Double.parseDouble(temp);
        }else{
            hargaSewaDriver = null;
        }
        temp = sharedPreferences.getString(KEY_RERATA, null);
        if(temp != null){
            rerataRating = Double.parseDouble(temp);
        }else{
            rerataRating = null;
        }

        fotoDriver = sharedPreferences.getString(KEY_FOTO, null);
        fotocopySIM = sharedPreferences.getString(KEY_SIM, null);
        bebasNAPZA = sharedPreferences.getString(KEY_NAPZA, null);
        kesehatanJiwa = sharedPreferences.getString(KEY_JIWA, null);
        kesehatanJasmani = sharedPreferences.getString(KEY_JASMANI, null);
        SKCK = sharedPreferences.getString(KEY_SKCK, null);
        api_token = sharedPreferences.getString(KEY_API, null);
        statusBerkas = sharedPreferences.getString(KEY_BERKAS, null);

        temp = sharedPreferences.getString(KEY_AKTIF, null);
        if(temp != null){
            isActive = Integer.parseInt(temp);
        }else{
            isActive = null;
        }

        return new Driver(idDriver, namaDriver, alamatDriver, tanggalLahirDriver,
                jenisKelaminDriver, email, password,
                noTelpDriver, bahasa, statusKetersediaanDriver, hargaSewaDriver, rerataRating,
                fotoDriver, fotocopySIM, bebasNAPZA, kesehatanJiwa, kesehatanJasmani, SKCK, isActive, api_token, statusBerkas);
    }

    public boolean checkLogin(){
        // Mengembalikan nilai is_login, jika sudah login otomatis nilai true jika tidak akan return false
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        // Melakukan clear data yang ada pada sharedPreferences, jangan lupa di commit agar data terubah
        editor.clear();
        editor.commit();
    }

}
