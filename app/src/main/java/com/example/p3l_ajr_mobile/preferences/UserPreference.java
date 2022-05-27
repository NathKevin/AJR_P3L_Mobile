package com.example.p3l_ajr_mobile.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.p3l_ajr_mobile.models.User;

public class UserPreference {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //    Mendefinisikan Key
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "idCustomer";
    public static final String KEY_NAMA = "namaCustomer";
    public static final String KEY_ALAMAT = "alamatCustomer";
    public static final String KEY_TL = "tanggalLahirCustomer";
    public static final String KEY_KELAMIN = "jenisKelaminCustomer";
    public static final String KEY_KATEGORI = "kategoriCustomer";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TELP = "noTelpCustomer";
    public static final String KEY_KTP = "KTP";
    public static final String KEY_SIM = "SIM";
    public static final String KEY_KP = "KP";
    public static final String KEY_RATING = "ratingAJR";
    public static final String KEY_PERFORMA = "performaAJR";
    public static final String KEY_API = "api_token";
    public static final String KEY_STATUS = "statusBerkas";
    public static final String KEY_WAITING = "waiting";

    public UserPreference(Context context){
        this.context=context;
        // penamaan bebas namun disini digunakan "userPreferences"
        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String idCustomer, String namaCustomer, String alamatCustomer, String tanggalLahirCustomer,
                         String jenisKelaminCustomer, String kategoriCustomer, String email, String password,
                         String noTelpCustomer, String KTP, String SIM, String KP, String ratingAJR,
                         String performaAJR, String api_token, String statusBerkas, String waiting){
        // Menyimpan data login ke sharedPreferences dengan key dan value
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, idCustomer);
        editor.putString(KEY_NAMA, namaCustomer);
        editor.putString(KEY_ALAMAT, alamatCustomer);
        editor.putString(KEY_TL, tanggalLahirCustomer);
        editor.putString(KEY_KELAMIN, jenisKelaminCustomer);
        editor.putString(KEY_KATEGORI, kategoriCustomer);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_TELP, noTelpCustomer);
        editor.putString(KEY_KTP, KTP);
        editor.putString(KEY_SIM, SIM);
        editor.putString(KEY_KP, KP);
        editor.putString(KEY_RATING, ratingAJR);
        editor.putString(KEY_PERFORMA, performaAJR);
        editor.putString(KEY_API, api_token);
        editor.putString(KEY_STATUS, statusBerkas);
        editor.putString(KEY_WAITING, waiting);

        // Jangan lupa commit karena kalo hanyan set editornya saja tidak commit akan sia-sia
        editor.commit();
    }

    public User getUserlogin(){
        // Mengembalikan object User untuk menampilkan data user jika user sudah login
        String idCustomer;
        String namaCustomer;
        String alamatCustomer;
        String tanggalLahirCustomer;
        String jenisKelaminCustomer;
        String kategoriCustomer;
        String email;
        String password;
        String noTelpCustomer;
        String KTP;
        String SIM;
        String KP;
        String ratingAJR;
        String performaAJR;
        String api_token;
        String statusBerkas;
        String waiting;

        idCustomer = sharedPreferences.getString(KEY_ID, null);
        namaCustomer = sharedPreferences.getString(KEY_NAMA, null);
        alamatCustomer = sharedPreferences.getString(KEY_ALAMAT, null);
        tanggalLahirCustomer = sharedPreferences.getString(KEY_TL, null);
        jenisKelaminCustomer = sharedPreferences.getString(KEY_KELAMIN, null);
        kategoriCustomer = sharedPreferences.getString(KEY_KATEGORI, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        noTelpCustomer = sharedPreferences.getString(KEY_TELP, null);
        KTP = sharedPreferences.getString(KEY_KTP, null);
        SIM = sharedPreferences.getString(KEY_SIM, null);
        KP = sharedPreferences.getString(KEY_KP, null);
        ratingAJR = sharedPreferences.getString(KEY_RATING, null);
        performaAJR = sharedPreferences.getString(KEY_PERFORMA, null);
        api_token = sharedPreferences.getString(KEY_API, null);
        statusBerkas = sharedPreferences.getString(KEY_STATUS, null);
        waiting = sharedPreferences.getString(KEY_WAITING, null);

        return new User(idCustomer, namaCustomer, alamatCustomer, tanggalLahirCustomer,
                jenisKelaminCustomer, kategoriCustomer, email, password,
                noTelpCustomer, KTP, SIM, KP, ratingAJR,
                performaAJR, api_token, statusBerkas, waiting);
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
