package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.TransitionBuilder;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.example.p3l_ajr_mobile.apis.LoginAPI;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Driver;
import com.example.p3l_ajr_mobile.models.DriverResponse;
import com.example.p3l_ajr_mobile.models.Pegawai;
import com.example.p3l_ajr_mobile.models.PegawaiResponse;
import com.example.p3l_ajr_mobile.models.Transaksi;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.models.UserResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RateDriverActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Transaksi transaksi;
    private RatingBar rbDriver;
    private TextInputEditText tietPerforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_driver);

        transaksi = (Transaksi) getIntent().getSerializableExtra("data");

        queue = Volley.newRequestQueue(this);

        rbDriver = findViewById(R.id.ratebar_driver);
        tietPerforma = findViewById(R.id.tv_performaDriver);
        Button btnSubmit = findViewById(R.id.btn_submitRateDriver);
        ImageView civDriver = findViewById(R.id.iv_rateDriver);
        TextView tvNamaDriver = findViewById(R.id.txtNamaDriver);

        Glide.with(this)
                .load(TransaksiAPI.GET_PICTURE + transaksi.getFotoDriver())
                .placeholder(R.drawable.camera)
                .into(civDriver);

        tvNamaDriver.setText(transaksi.getNamaDriver());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRerataDriver();
            }
        });
    }

    private void updateRerataDriver(){
        Float temp = rbDriver.getRating();
        Double rerataRating = (double) temp;

        Driver driver = new Driver(rerataRating);

        StringRequest stringRequest = new StringRequest(PUT, DriverAPI.UPDATE_RATE_URL + transaksi.getIdDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                updateRatePerformaTransaksi();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RateDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RateDriverActivity.this, e.getMessage(),
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
            public byte[] getBody() throws AuthFailureError{
                Gson gson = new Gson();
                String requestBody = gson.toJson(driver);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    private void updateRatePerformaTransaksi(){
        Float temp = rbDriver.getRating();
        Integer rateDriver = Math.round(temp);
        String performaDriver = tietPerforma.getText().toString().trim();

        Transaksi transaksi2 = new Transaksi(rateDriver, performaDriver);
        Log.i("TestIdTransaksi",transaksi.getIdTransaksi());
        StringRequest stringRequest = new StringRequest(PUT, TransaksiAPI.UPDATE_RATE_PERFORMA_TRANSAKSI + transaksi.getIdTransaksi(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RateDriverActivity.this, " Terimakasih atas tanggapan anda ", Toast.LENGTH_SHORT).show();
                Intent back = new Intent(RateDriverActivity.this, RiwayatActivity.class);
                startActivity(back);
                finish();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RateDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RateDriverActivity.this, e.getMessage(),
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
            public byte[] getBody() throws AuthFailureError{
                Gson gson = new Gson();
                String requestBody = gson.toJson(transaksi2);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }
}