package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_mobile.apis.LoginAPI;
import com.example.p3l_ajr_mobile.models.Driver;
import com.example.p3l_ajr_mobile.models.DriverResponse;
import com.example.p3l_ajr_mobile.models.Pegawai;
import com.example.p3l_ajr_mobile.models.PegawaiResponse;
import com.example.p3l_ajr_mobile.models.User;
import com.example.p3l_ajr_mobile.models.UserResponse;
import com.example.p3l_ajr_mobile.preferences.DriverPreference;
import com.example.p3l_ajr_mobile.preferences.PegawaiPreference;
import com.example.p3l_ajr_mobile.preferences.UserPreference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue queue;
    private UserPreference userPreference;
    private DriverPreference driverPreference;
    private PegawaiPreference pegawaiPreference;
    private TextInputEditText tiet_email, tiet_pass;
    private Button btnClear, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(this);
        userPreference = new UserPreference(LoginActivity.this);
        pegawaiPreference = new PegawaiPreference(LoginActivity.this);
        driverPreference = new DriverPreference(LoginActivity.this);

        tiet_email = findViewById(R.id.email_text);
        tiet_pass = findViewById(R.id.pass_text);
        btnClear = findViewById(R.id.btn_clear);
        btnLogin = findViewById(R.id.btn_login);

        checkLogin();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiet_email.setText("");
                tiet_pass.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    login();
                }else{
                    Toast.makeText(LoginActivity.this, "Email Atau Password Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(){
        String email = tiet_email.getText().toString().trim();
        String pass = tiet_pass.getText().toString().trim();

        User user = new User(email,pass);

        StringRequest stringRequest = new StringRequest(POST, LoginAPI.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserResponse userResponse = gson.fromJson(response, UserResponse.class);
                PegawaiResponse pegawaiResponse = gson.fromJson(response, PegawaiResponse.class);
                DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                if(userResponse.getUser().getIdCustomer() != null){
                    userPreference.setLogin(userResponse.getUser().getIdCustomer(), userResponse.getUser().getNamaCustomer(), userResponse.getUser().getAlamatCustomer(),
                            userResponse.getUser().getTanggalLahirCustomer(), userResponse.getUser().getJenisKelaminCustomer(), userResponse.getUser().getKategoriCustomer(),
                            userResponse.getUser().getEmail(),userResponse.getUser().getPassword(), userResponse.getUser().getNoTelpCustomer(), userResponse.getUser().getKTP(),
                            userResponse.getUser().getSIM(), userResponse.getUser().getKP(), userResponse.getUser().getRatingAJR(), userResponse.getUser().getPerformaAJR(),
                            userResponse.getApi_token(), userResponse.getUser().getStatusBerkas(), userResponse.getUser().getWaiting());
                    checkLogin();
                }else if(pegawaiResponse.getPegawai().getIdPegawai() != null){
                    if(pegawaiResponse.getPegawai().getIdRole() == 1){
                        Pegawai temp = pegawaiResponse.getPegawai();
                        pegawaiPreference.setLogin(temp.getIdPegawai(),temp.getIdRole(), temp.getNamaPegawai(), temp.getAlamatPegawai(), temp.getTanggalLahirPegawai(),
                                temp.getJenisKelaminPegawai(), temp.getEmail(), temp.getPassword(), temp.getNoTelpPegawai(), temp.getFotoPegawai(), temp.getActive(),
                                pegawaiResponse.getApi_token());
                        checkLogin();
                    }else{
                        Toast.makeText(LoginActivity.this, "CS & Admin Login melalui website", Toast.LENGTH_SHORT).show();
                    }
                }else if(driverResponse.getDriver().getIdDriver() != null){
                    Driver temp1 = driverResponse.getDriver();
                    driverPreference.setLogin(temp1.getIdDriver(),temp1.getNamaDriver(), temp1.getAlamatDriver(), temp1.getTanggalLahirDriver(), temp1.getJenisKelaminDriver(),
                            temp1.getEmail(), temp1.getPassword(), temp1.getNoTelpDriver(), temp1.getBahasa(), temp1.getStatusKetersediaanDriver(), temp1.getHargaSewaDriver(),
                            temp1.getRerataRating(), temp1.getFotoDriver(), temp1.getFotocopySIM(), temp1.getBebasNAPZA(), temp1.getKesehatanJiwa(), temp1.getKesehatanJasmani(),
                            temp1.getSKCK(), temp1.getIsActive(), driverResponse.getApi_token(), temp1.getStatusBerkas());
                    checkLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "Oops... Something is not right", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(LoginActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(),
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
                String requestBody = gson.toJson(user);
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

    private boolean validateForm(){
        // Check username & Password is empty or not
        if(tiet_email.getText().toString().trim().isEmpty() || tiet_pass.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private void checkLogin(){
        if(userPreference.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else if(pegawaiPreference.checkLogin()){
            startActivity(new Intent(LoginActivity.this, PegawaiActivity.class));
            finish();
        }else if(driverPreference.checkLogin()){
            startActivity(new Intent(LoginActivity.this, DriverActivity.class));
            finish();
        }
    }
}