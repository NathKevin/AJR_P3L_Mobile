package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_mobile.adapters.RiwayatDriverAdapter;
import com.example.p3l_ajr_mobile.adapters.RiwayatTransaksiAdapter;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Driver;
import com.example.p3l_ajr_mobile.models.TransaksiResponse;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.preferences.DriverPreference;
import com.example.p3l_ajr_mobile.preferences.UserPreference;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RiwayatDriverActivity extends AppCompatActivity {

    DriverPreference driverPreference;
    Driver driver;
    private RiwayatDriverAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_driver);

        driverPreference = new DriverPreference(this);
        driver = driverPreference.getDriverlogin();

        queue = Volley.newRequestQueue(this);

        rvTransaksi = findViewById(R.id.rv_riwayatDriver_new);

        getAllTransaksi();
    }

    private void getAllTransaksi() {
        StringRequest stringRequest = new StringRequest(GET, TransaksiAPI.GET_ALL_BY_DRIVER + driver.getIdDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                adapter = new RiwayatDriverAdapter(transaksiResponse.getTransaksiList(), RiwayatDriverActivity.this);
                rvTransaksi.setLayoutManager(new LinearLayoutManager(RiwayatDriverActivity.this, LinearLayoutManager.VERTICAL, false));
                rvTransaksi.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(RiwayatDriverActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RiwayatDriverActivity.this, e.getMessage(),
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
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }
}