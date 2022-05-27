package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_mobile.adapters.MobilAdapter;
import com.example.p3l_ajr_mobile.adapters.PromoAdapter;
import com.example.p3l_ajr_mobile.apis.MobilAPI;
import com.example.p3l_ajr_mobile.apis.PromoAPI;
import com.example.p3l_ajr_mobile.models.MobilResponse;
import com.example.p3l_ajr_mobile.models.Promo;
import com.example.p3l_ajr_mobile.models.PromoResponse;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.preferences.UserPreference;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaftarPromoActivity extends AppCompatActivity {

    private UserPreference userPreference;
    private User user;
    private PromoAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvPromo;
    private MaterialCardView hiddenCard;
    private List<Promo> promoList = new ArrayList<Promo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_promo);

        userPreference = new UserPreference(this);
        user = userPreference.getUserlogin();

        queue = Volley.newRequestQueue(this);

        rvPromo = findViewById(R.id.rv_promo_new);
//        hiddenCard = findViewById(R.id.empty_promo);

        getAllPromo();
    }

    private void getAllPromo() {
        StringRequest stringRequest = new StringRequest(GET, PromoAPI.GET_ALL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PromoResponse promoResponse = gson.fromJson(response, PromoResponse.class);
                checkPromo(promoResponse);
                Toast.makeText(DaftarPromoActivity.this, promoResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DaftarPromoActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DaftarPromoActivity.this, e.getMessage(),
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

    public void checkPromo(PromoResponse promoResponse){
        for(int i=0 ;i<promoResponse.getPromoList().size(); i++){
            if(promoResponse.getPromoList().get(i).getKode().equalsIgnoreCase("WKN")){
                if(promoResponse.getDay().equalsIgnoreCase("Saturday") || promoResponse.getDay().equalsIgnoreCase("Sunday") ){
                    promoList.add(promoResponse.getPromoList().get(i));
                }
            }else if(promoResponse.getPromoList().get(i).getKode().equalsIgnoreCase("BDAY")){
                if(promoResponse.getDate().equals(user.getTanggalLahirCustomer())){
                    promoList.add(promoResponse.getPromoList().get(i));
                }
            }else if(promoResponse.getPromoList().get(i).getKode().equalsIgnoreCase("MHS")){
                if(user.getKP() != null){
                    promoList.add(promoResponse.getPromoList().get(i));
                }
            }else if(promoResponse.getPromoList().get(i).getKode().equalsIgnoreCase("MDK")){
                promoList.add(promoResponse.getPromoList().get(i));
            }
        }

        if(promoList == null){
            TransitionManager.beginDelayedTransition(hiddenCard, new AutoTransition());
//            hiddenCard.setVisibility(View.VISIBLE);
            rvPromo.setVisibility(View.GONE);

        }else{
            adapter = new PromoAdapter(promoList, this);
            rvPromo.setLayoutManager(new LinearLayoutManager(DaftarPromoActivity.this, LinearLayoutManager.VERTICAL, false));
            rvPromo.setAdapter(adapter);
        }

    }
}