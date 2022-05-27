package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.p3l_ajr_mobile.R;
import com.example.p3l_ajr_mobile.apis.DriverAPI;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Driver;
import com.example.p3l_ajr_mobile.models.Transaksi;
import com.example.p3l_ajr_mobile.preferences.DriverPreference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class DriverProfilActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Calendar calendar = Calendar.getInstance();
    private DriverPreference driverPreference;
    private Driver driver;
    private ImageView ivDriver;
    private TextInputEditText tietNama, tietTL, tietAlamat, tietKelamin, tietNoTelp, tietBahasa;
    private TextInputEditText tietEmail, tietOldPass, tietNewPass, tierConfirmPass;
    private Button btnUbahProfil, btnUbahEmail, btnUbahPass;
    private String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profil);

        queue = Volley.newRequestQueue(this);

        driverPreference = new DriverPreference(this);
        driver = driverPreference.getDriverlogin();

        nama = driver.getNamaDriver();

        ivDriver = findViewById(R.id.iv_gambarDriver);
        tietNama = findViewById(R.id.tiet_namaDriver);
        tietAlamat = findViewById(R.id.tiet_alamatDriver);
        tietBahasa = findViewById(R.id.tiet_bahasaDriver);
        tietKelamin = findViewById(R.id.tiet_jenisKelaminDriver);
        tietTL = findViewById(R.id.tiet_tanggalLahirDriver);
        tietNoTelp = findViewById(R.id.tiet_noTelpDriver);
        btnUbahEmail = findViewById(R.id.btn_ubahEmail);
        btnUbahProfil = findViewById(R.id.btn_ubah);
        btnUbahPass = findViewById(R.id.btn_ubahPass);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        tietTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DriverProfilActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Glide.with(this)
                .load(DriverAPI.GET_PICTURE + driver.getFotoDriver())
                .placeholder(R.drawable.camera)
                .into(ivDriver);

        tietNama.setText(driver.getNamaDriver());
        tietTL.setText(driver.getTanggalLahirDriver());
        tietAlamat.setText(driver.getAlamatDriver());
        tietKelamin.setText(driver.getJenisKelaminDriver());
        tietNoTelp.setText(driver.getNoTelpDriver());
        tietBahasa.setText(driver.getBahasa());

        btnUbahPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialogPass = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_ubah_password_driver, null);
                builder.setView(dialogPass);

                Button confirm, batal;

                tietOldPass = dialogPass.findViewById(R.id.tiet_oldPassword);
                tietNewPass = dialogPass.findViewById(R.id.tiet_password);
                tierConfirmPass = dialogPass.findViewById(R.id.tiet_confirmPassword);
                confirm = dialogPass.findViewById(R.id.btn_confirm);
                batal = dialogPass.findViewById(R.id.btn_back);

                AlertDialog newDialog = builder.create();
                newDialog.show();

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Konfirmasi!");
                        builder.setMessage("Anda yakin ingin mengubah password?");
                        builder.setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updatePassDriver();
                                dialog.dismiss();
                                newDialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog newDialog2 = builder.create();
                        newDialog2.show();
                    }
                });

                batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newDialog.dismiss();
                    }
                });
            }
        });

        btnUbahEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialogEmail = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_ubah_email_driver, null);
                builder.setView(dialogEmail);

                Button confirm, batal;

                tietEmail = dialogEmail.findViewById(R.id.tiet_ubahEmail);
                tietEmail.setText(driver.getEmail());
                confirm = dialogEmail.findViewById(R.id.btn_confirm);
                batal = dialogEmail.findViewById(R.id.btn_back);

                AlertDialog newDialog = builder.create();
                newDialog.show();

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Konfirmasi!");
                        builder.setMessage("Anda yakin ingin mengubah email?");
                        builder.setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateEmailDriver();
                                dialog.dismiss();
                                newDialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog newDialog2 = builder.create();
                        newDialog2.show();
                    }
                });

                batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newDialog.dismiss();
                    }
                });

            }
        });

        btnUbahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Apakah anda yakin ingin mengubah Profil?");
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
        });

    }

    private void updateLabel(){
        String myFormat="YYYY-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        tietTL.setText(dateFormat.format(calendar.getTime()));
    }

    private void updateProfilDriver(){
        String namaDriver = tietNama.getText().toString().trim();
        String alamatDriver = tietAlamat.getText().toString().trim();
        String tanggalLahirDriver = tietTL.getText().toString().trim();
        String jenisKelaminDriver = tietKelamin.getText().toString().trim();
        String noTelpDriver = tietNoTelp.getText().toString().trim();
        String bahasa = tietBahasa.getText().toString().trim();
        Driver updateDriver = new Driver(namaDriver, alamatDriver, tanggalLahirDriver, jenisKelaminDriver, noTelpDriver, bahasa);
        StringRequest stringRequest = new StringRequest(PUT, DriverAPI.UPDATE_PROFIL + driver.getIdDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                driverPreference.setUpdateDriver(namaDriver, alamatDriver, tanggalLahirDriver, jenisKelaminDriver, noTelpDriver, bahasa);
                driver = driverPreference.getDriverlogin();
                nama = namaDriver;
                Log.i("TESTING",nama);
                Toast.makeText(DriverProfilActivity.this, " Update Berhasil ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DriverProfilActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DriverProfilActivity.this, e.getMessage(),
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
                String requestBody = gson.toJson(updateDriver);
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

    @Override
    public void onBackPressed() {

        Intent back = new Intent();
        back.putExtra("driver", nama);
        setResult(RESULT_OK, back);
        finish();
    }

    private void updateEmailDriver(){
        String email = tietEmail.getText().toString().trim();
        Driver updateDriver = new Driver(email);
        StringRequest stringRequest = new StringRequest(PUT, DriverAPI.UPDATE_EMAIL + driver.getIdDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                driverPreference.setUpdateEmail(email);
                driver = driverPreference.getDriverlogin();
                Toast.makeText(DriverProfilActivity.this, " Update Email Berhasil ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DriverProfilActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DriverProfilActivity.this, e.getMessage(),
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
                String requestBody = gson.toJson(updateDriver);
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

    private void updatePassDriver(){
        String password = tietNewPass.getText().toString().trim();
        String confirmPassword = tierConfirmPass.getText().toString().trim();
        String oldPassword = tietOldPass.getText().toString().trim();
        if(password.equals(confirmPassword)){
            Driver updateDriver = new Driver(password,oldPassword);
            StringRequest stringRequest = new StringRequest(PUT, DriverAPI.UPDATE_PASS + driver.getIdDriver(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    driverPreference.setUpdatePassword(password);
                    driver = driverPreference.getDriverlogin();
                    Toast.makeText(DriverProfilActivity.this, " Update Password Berhasil ", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(DriverProfilActivity.this,
                                errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(DriverProfilActivity.this, e.getMessage(),
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
                    String requestBody = gson.toJson(updateDriver);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override
                public String getBodyContentType(){
                    return "application/json";
                }
            };
            // Menambahkan request ke request queue
            queue.add(stringRequest);
        }else{
            Toast.makeText(DriverProfilActivity.this, " Konfirmasi Password tidak sesuai ", Toast.LENGTH_SHORT).show();
        }
    }
}