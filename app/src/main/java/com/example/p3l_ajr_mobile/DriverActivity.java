package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.PUT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.p3l_ajr_mobile.apis.DriverAPI;
import com.example.p3l_ajr_mobile.models.Driver;
import com.example.p3l_ajr_mobile.models.DriverResponse;
import com.example.p3l_ajr_mobile.models.PegawaiResponse;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.models.UserResponse;
import com.example.p3l_ajr_mobile.preferences.DriverPreference;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DriverActivity extends AppCompatActivity {

    private RequestQueue queue;
    private DriverPreference driverPreference;
    private Button btnProfil,btnLogout, btnTampil, btnUbah;
    private Driver driver;
    private TextView driverName;
    private Chip statusAktif;
    private ImageView ivDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        queue = Volley.newRequestQueue(this);

        driverPreference = new DriverPreference(this);
        driver = driverPreference.getDriverlogin();
        Log.i("TEST3",driver.getIsActive().toString());

        driverName = findViewById(R.id.tvnamaDriver);
        btnProfil = findViewById(R.id.btn_profile);
        btnLogout = findViewById(R.id.btn_logout);
        btnTampil = findViewById(R.id.btn_tampilRiwayat);
        btnUbah = findViewById(R.id.btn_ubahStatus);
        statusAktif = findViewById(R.id.chip_statusAktifDriver);
        ivDriver = findViewById(R.id.icon);

        Glide.with(this)
                .load(DriverAPI.GET_PICTURE + driver.getFotoDriver())
                .placeholder(R.drawable.camera)
                .into(ivDriver);

        driverName.setText(driver.getNamaDriver());
        checkLogin();

        if(driver.getIsActive() == 1){
            statusAktif.setChipBackgroundColorResource(R.color.selesai);
            statusAktif.setText("Aktif - Menerima Pesanan");
        }else{
            statusAktif.setChipBackgroundColorResource(R.color.ditolak);
            statusAktif.setText("Nonaktif - Menolak Pesanan");
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverPreference.logout();
                Toast.makeText(DriverActivity.this, "Logout Success!", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRiwayat = new Intent(DriverActivity.this, RiwayatDriverActivity.class);
                startActivity(toRiwayat);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfil = new Intent(DriverActivity.this, DriverProfilActivity.class);
                startActivityForResult(toProfil,1);
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(driver.getIsActive() == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Apakah anda yakin ingin menonaktifkan status anda? Anda tidak akan mendapatkan pesanan dari Customer Service kami selama status anda Nonaktif.");
                    builder.setTitle("Konfirmasi!");
                    builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateProfilDriver();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Apakah anda yakin ingin mengaktifkan status anda? Anda akan mendapatkan pesanan dari Customer Service kami jika anda dipilih oleh Customer.");
                    builder.setTitle("Konfirmasi!");
                    builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateProfilDriver();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

    }

    private void checkLogin(){
        // this function will check if user login, akan memunculkan toast jika tidak redirect ke login activity
        if(!driverPreference.checkLogin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Toast.makeText(DriverActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Log.i("TEST1", "MASUK SINI");
            if (resultCode == RESULT_OK) {
                Log.i("TEST2", "MASUK SINI2");
                String newName = data.getStringExtra("driver");
                driverName.setText(newName);
            }
        }
    }

    private void updateProfilDriver(){
        StringRequest stringRequest = new StringRequest(PUT, DriverAPI.UPDATE_STATUS + driver.getIdDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                driverPreference.setUpdateStatus(driverResponse.getDriver2().getIsActive());
                driver = driverPreference.getDriverlogin();
                cekStatus(driverResponse.getDriver2().getIsActive());
                Toast.makeText(DriverActivity.this, " Update Status Berhasil ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DriverActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    public void cekStatus(Integer status){
        if(status == 1){
            statusAktif.setChipBackgroundColorResource(R.color.selesai);
            statusAktif.setText("Aktif - Menerima Pesanan");
        }else{
            statusAktif.setChipBackgroundColorResource(R.color.ditolak);
            statusAktif.setText("Nonaktif - Menolak Pesanan");
        }
    }

}