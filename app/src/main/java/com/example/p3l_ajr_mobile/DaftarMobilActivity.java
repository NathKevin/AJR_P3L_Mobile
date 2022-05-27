package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_mobile.adapters.MobilAdapter;
import com.example.p3l_ajr_mobile.apis.MobilAPI;
import com.example.p3l_ajr_mobile.models.Mobil;
import com.example.p3l_ajr_mobile.models.MobilResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DaftarMobilActivity extends AppCompatActivity {

    private MobilAdapter adapter;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil);

        queue = Volley.newRequestQueue(this);

        RecyclerView rvMobil = findViewById(R.id.rv_mobil_new);
        adapter = new MobilAdapter(new ArrayList<>(), this);

//        int orientation = getResources().getConfiguration().orientation;
//        int spanCount = 0; //count item for each row
//        if(orientation == Configuration.ORIENTATION_PORTRAIT){
//            spanCount = 1;
//        }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
//            spanCount = 2;
//        }
        rvMobil.setLayoutManager(new LinearLayoutManager(DaftarMobilActivity.this, LinearLayoutManager.VERTICAL, false));
        rvMobil.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getAllMobil();
    }

    private void getAllMobil() {
        StringRequest stringRequest = new StringRequest(GET, MobilAPI.GET_ALL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MobilResponse mobilResponse = gson.fromJson(response, MobilResponse.class);
                adapter.setMobilList(mobilResponse.getMobilList());
                adapter.notifyDataSetChanged();
                Toast.makeText(DaftarMobilActivity.this, mobilResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DaftarMobilActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DaftarMobilActivity.this, e.getMessage(),
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