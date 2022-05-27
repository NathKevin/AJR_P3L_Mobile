package com.example.p3l_ajr_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.preferences.UserPreference;

public class ProfilActivity extends AppCompatActivity {

    private TextView tvNama, tvAlamat, tvTL, tvKelamin, tvEmail, tvTelp;
    private UserPreference userPreference;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        userPreference = new UserPreference(this);
        user = userPreference.getUserlogin();

        tvNama = findViewById(R.id.tv_namaCustomer);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTL = findViewById(R.id.tv_tanggalLahirCustomer);
        tvEmail = findViewById(R.id.tv_email);
        tvKelamin = findViewById(R.id.tv_jenisKelaminCustomer);
        tvTelp = findViewById(R.id.tv_noTelpCustomer);

        tvNama.setText(user.getNamaCustomer());
        tvAlamat.setText(user.getAlamatCustomer());
        tvTL.setText(user.getTanggalLahirCustomer());
        tvEmail.setText(user.getEmail());
        tvKelamin.setText(user.getJenisKelaminCustomer());
        tvTelp.setText(user.getNoTelpCustomer());
    }
}