package com.example.p3l_ajr_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.preferences.UserPreference;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private UserPreference userPreference;
    private Button btnProfil,btnLogout;
    private User user;
    private TextView userName;
    private MaterialCardView cardMobil, cardPromo, cardRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreference = new UserPreference(this);
        userName = findViewById(R.id.txtUser);
        btnLogout = findViewById(R.id.btn_logout);
        btnProfil = findViewById(R.id.btn_profile);

        cardMobil = findViewById(R.id.cardgrid1);
        cardPromo = findViewById(R.id.cardgrid2);
        cardRiwayat = findViewById(R.id.cardgrid3);

        user = userPreference.getUserlogin();

        userName.setText(user.getNamaCustomer());
        checkLogin();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPreference.logout();
                Toast.makeText(MainActivity.this, "Logout Success!", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfilActivity.class));
            }
        });

        cardMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaftarMobilActivity.class));
            }
        });

        cardPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaftarPromoActivity.class));
            }
        });

        cardRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RiwayatActivity.class));
            }
        });
    }

    private void checkLogin(){
        // this function will check if user login, akan memunculkan toast jika tidak redirect ke login activity
        if(!userPreference.checkLogin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Toast.makeText(MainActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
        }
    }
}