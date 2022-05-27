package com.example.p3l_ajr_mobile.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ipsec.ike.IkeSaProposal;

import com.example.p3l_ajr_mobile.models.Pegawai;
import com.example.p3l_ajr_mobile.models.User;

public class PegawaiPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "idPegawai";
    public static final String KEY_ROLE = "idRole";
    public static final String KEY_NAMA = "namaPegawai";
    public static final String KEY_ALAMAT = "alamatPegawai";
    public static final String KEY_TL = "tanggalLahirPegawai";
    public static final String KEY_KELAMIN = "jenisKelaminPegawai";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TELP = "noTelpPegawai";
    public static final String KEY_FOTO = "fotoPegawai";
    public static final String KEY_AKTIF = "isActive";
    public static final String KEY_API = "api_token";

    public PegawaiPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pegawaiPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(Long idPegawai, Long idRole, String namaPegawai, String alamatPegawai, String tanggalLahirPegawai, String jenisKelaminPegawai,
                         String email, String password, String noTelpPegawai, String fotoPegawai, Integer isActive, String api_token){
        // Menyimpan data login ke sharedPreferences dengan key dan value
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, idPegawai.toString());
        editor.putString(KEY_ROLE, idRole.toString());
        editor.putString(KEY_NAMA, namaPegawai);
        editor.putString(KEY_ALAMAT, alamatPegawai);
        editor.putString(KEY_TL, tanggalLahirPegawai);
        editor.putString(KEY_KELAMIN, jenisKelaminPegawai);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_TELP, noTelpPegawai);
        editor.putString(KEY_FOTO, fotoPegawai);
        editor.putString(KEY_AKTIF, isActive.toString());
        editor.putString(KEY_API, api_token);

        // Jangan lupa commit karena kalo hanyan set editornya saja tidak commit akan sia-sia
        editor.commit();
    }

    public Pegawai getPegawailogin(){
        // Mengembalikan object User untuk menampilkan data user jika user sudah login
        Long idPegawai;
        Long idRole;
        String namaPegawai;
        String alamatPegawai;
        String tanggalLahirPegawai;
        String jenisKelaminPegawai;
        String email;
        String password;
        String noTelpPegawai;
        String fotoPegawai;
        Integer isActive;
        String api_token;
        String temp;

        temp = sharedPreferences.getString(KEY_ID, null);
        if(temp != null){
            idPegawai = Long.parseLong(temp);
        }else{
            idPegawai = null;
        }
        temp = sharedPreferences.getString(KEY_ROLE, null);
        if(temp != null){
            idRole = Long.parseLong(temp);
        }else{
            idRole = null;
        }

        namaPegawai = sharedPreferences.getString(KEY_NAMA, null);
        alamatPegawai = sharedPreferences.getString(KEY_ALAMAT, null);
        tanggalLahirPegawai = sharedPreferences.getString(KEY_TL, null);
        jenisKelaminPegawai = sharedPreferences.getString(KEY_KELAMIN, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        noTelpPegawai = sharedPreferences.getString(KEY_TELP, null);
        fotoPegawai = sharedPreferences.getString(KEY_FOTO, null);
        temp = sharedPreferences.getString(KEY_AKTIF, null);
        if(temp != null){
            isActive = Integer.parseInt(temp);
        }else{
            isActive = null;
        }
        api_token = sharedPreferences.getString(KEY_API, null);

        return new Pegawai(idPegawai, idRole, namaPegawai, alamatPegawai, tanggalLahirPegawai, jenisKelaminPegawai,
                email, password, noTelpPegawai, fotoPegawai, isActive, api_token);
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
