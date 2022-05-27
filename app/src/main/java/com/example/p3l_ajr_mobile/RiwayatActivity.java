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
import com.example.p3l_ajr_mobile.adapters.MobilAdapter;
import com.example.p3l_ajr_mobile.adapters.RiwayatTransaksiAdapter;
import com.example.p3l_ajr_mobile.apis.MobilAPI;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.MobilResponse;
import com.example.p3l_ajr_mobile.models.TransaksiResponse;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.preferences.UserPreference;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatActivity extends AppCompatActivity {

    UserPreference userPreference;
    User user;
    private RiwayatTransaksiAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        userPreference = new UserPreference(this);
        user = userPreference.getUserlogin();

        queue = Volley.newRequestQueue(this);

        rvTransaksi = findViewById(R.id.rv_transaksi_new);

        getAllTransaksi();
    }

    private void getAllTransaksi() {
        StringRequest stringRequest = new StringRequest(GET, TransaksiAPI.GET_ALL + user.getIdCustomer(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                adapter = new RiwayatTransaksiAdapter(transaksiResponse.getTransaksiList(), RiwayatActivity.this);
                rvTransaksi.setLayoutManager(new LinearLayoutManager(RiwayatActivity.this, LinearLayoutManager.VERTICAL, false));
                rvTransaksi.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(RiwayatActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RiwayatActivity.this, e.getMessage(),
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