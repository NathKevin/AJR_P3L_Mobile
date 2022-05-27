package com.example.p3l_ajr_mobile;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.p3l_ajr_mobile.adapters.RiwayatTransaksiAdapter;
import com.example.p3l_ajr_mobile.apis.DriverAPI;
import com.example.p3l_ajr_mobile.apis.LaporanAPI;
import com.example.p3l_ajr_mobile.apis.PegawaiAPI;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Laporan;
import com.example.p3l_ajr_mobile.models.LaporanResponse;
import com.example.p3l_ajr_mobile.models.Pegawai;
import com.example.p3l_ajr_mobile.models.TransaksiResponse;
import com.example.p3l_ajr_mobile.preferences.PegawaiPreference;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PegawaiActivity extends AppCompatActivity {

    private Calendar calendar = Calendar.getInstance();
    private Calendar calendar2 = Calendar.getInstance();
    private PegawaiPreference pegawaiPreference;
    private Pegawai pegawai;
    private Button btnLogout;
    private ImageView ivGambar;
    private TextView namaPegawai;
    private MaterialCardView card1, card2, card3, card4, card5;
    DatePickerDialog.OnDateSetListener date, date2;

    private Integer cekInputTanggal;
    private TextInputEditText tglMulai, tglSelesai;
    private TextView description;

    private String url;
    private List<Laporan> laporanList;
    private RequestQueue queue;
    private String headerLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);

        queue = Volley.newRequestQueue(this);

        pegawaiPreference = new PegawaiPreference(this);
        pegawai = pegawaiPreference.getPegawailogin();

        btnLogout = findViewById(R.id.btn_logout);
        ivGambar = findViewById(R.id.iv_gambarPegawai);
        namaPegawai = findViewById(R.id.txtNamaPegawai);
        card1 = findViewById(R.id.cardgrid1);
        card2 = findViewById(R.id.cardgrid2);
        card3 = findViewById(R.id.cardgrid3);
        card4 = findViewById(R.id.cardgrid4);
        card5 = findViewById(R.id.cardgrid5);

        Glide.with(this)
                .load(PegawaiAPI.GET_PICTURE + pegawai.getFotoPegawai())
                .placeholder(R.drawable.camera)
                .into(ivGambar);

        namaPegawai.setText("Manager " + pegawai.getNamaPegawai());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegawaiPreference.logout();
                Toast.makeText(PegawaiActivity.this, "Logout Success!", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH,month);
                calendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel2();
            }
        };

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerLaporan = "Penyewaan Mobil";
                url = LaporanAPI.GET_LAP1;
                makeDialog(1);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerLaporan = "Pendapatan Transaksi";
                url = LaporanAPI.GET_LAP2;
                makeDialog(2);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerLaporan = "Top 5 Driver";
                url = LaporanAPI.GET_LAP3;
                makeDialog(3);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerLaporan = "Performa Driver";
                url = LaporanAPI.GET_LAP4;
                makeDialog(4);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerLaporan = "Top 5 Customer";
                url = LaporanAPI.GET_LAP5;
                makeDialog(5);
            }
        });
    }

    private void makeDialog(Integer temp){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialog = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_tanggal_laporan, null);
        builder.setView(dialog);

        Button confirm, batal;

        confirm = dialog.findViewById(R.id.btn_confirm);
        batal = dialog.findViewById(R.id.btn_back);
        tglMulai = dialog.findViewById(R.id.tiet_tglMulai);
        tglSelesai = dialog.findViewById(R.id.tiet_tglSelesai);
        description = dialog.findViewById(R.id.txtDescription1);
        tglMulai.setText("");
        tglSelesai.setText("");
        description.setText("Laporan " + headerLaporan);

        AlertDialog newDialog = builder.create();
        newDialog.show();

        tglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PegawaiActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PegawaiActivity.this, date2, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekInputLaporan(temp);
                newDialog.dismiss();
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tglMulai.setText(null);
                tglSelesai.setText(null);
                newDialog.dismiss();
            }
        });
    }

    private void cekInputLaporan(Integer temp){
        if(tglMulai.getText().toString().isEmpty() && tglSelesai.getText().toString().isEmpty()){
            Toast.makeText(this,"Input Tanggal Mulai & Tanggal Selesai tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else{
            getDataTransaksi(temp);
        }
    }

    private void updateLabel(){
        String myFormat="YYYY-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        tglMulai.setText(dateFormat.format(calendar.getTime()));
    }

    private void updateLabel2(){
        String myFormat="YYYY-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        tglSelesai.setText(dateFormat.format(calendar2.getTime()));
    }

    private void checkLogin(){
        // this function will check if user login, akan memunculkan toast jika tidak redirect ke login activity
        if(!pegawaiPreference.checkLogin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Toast.makeText(PegawaiActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataTransaksi(Integer temp) {
        StringRequest stringRequest = new StringRequest(GET, url + tglMulai.getText().toString() +'/' + tglSelesai.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanResponse laporanResponse = gson.fromJson(response, LaporanResponse.class);
                laporanList = laporanResponse.getLaporanList();
                Toast.makeText(PegawaiActivity.this, laporanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(laporanList == null){
                    Toast.makeText(PegawaiActivity.this,"Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }else{
                    if(temp == 1){
                        try {
                            cetakPdf1();
                        } catch (FileNotFoundException | DocumentException e) {
                            e.printStackTrace();
                        }
                    }else if(temp == 2){
                        try {
                            cetakPdf2();
                        } catch (FileNotFoundException | DocumentException e) {
                            e.printStackTrace();
                        }
                    }else if(temp == 3){
                        try {
                            cetakPdf3();
                        } catch (FileNotFoundException | DocumentException e) {
                            e.printStackTrace();
                        }
                    }else if(temp == 4){
                        try {
                            cetakPdf4();
                        } catch (FileNotFoundException | DocumentException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            cetakPdf5();
                        } catch (FileNotFoundException | DocumentException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(PegawaiActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(PegawaiActivity.this, e.getMessage(),
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

    private void cetakPdf1() throws FileNotFoundException, DocumentException {
         /*
         * Untuk Android 11 nanti file pdf tidak bisa diakses lewat file
        manager HP langsung
         * jadi harus konek lewat PC gara gara implementasi Scoped Storage.
         *
         * Kalau mau biar bisa di android 11 bisa pelajari sendiri tentang
        penggunaan MediaStorage
         * */
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = "Laporan " + headerLaporan + " - " + currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("Laporan " + headerLaporan + " \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        PdfPCell cellSupplier2 = new PdfPCell();
        cellSupplier2.setPaddingLeft(20);
        cellSupplier2.setPaddingBottom(10);
        cellSupplier2.setBorder(Rectangle.NO_BORDER);

        Paragraph tanggalMulai = new Paragraph(
                "Tanggal Mulai \n" + tglMulai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(tanggalMulai);
        tanggalMulai.setAlignment(Element.ALIGN_LEFT);
        tables.addCell(cellSupplier);

        Paragraph tanggalSelesai = new Paragraph(
                "Tanggal Selesai \n" + tglSelesai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier2.addElement(tanggalSelesai);
        tanggalSelesai.setAlignment(Element.ALIGN_RIGHT);
        tables.addCell(cellSupplier2);

        document.add(tables);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);
        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("Tipe Mobil"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Peminjaman"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Pendapatan"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        // masukan data pegawai jadi baris
        for (Laporan l : laporanList) {
            tableData.addCell(l.getTipeMobil());
            tableData.addCell(l.getNamaMobil());
            tableData.addCell(String.valueOf(l.getJumlahPeminjaman()));
            tableData.addCell(String.valueOf(l.getPendapatan()));
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_LEFT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "Laporan " + headerLaporan + " Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPdf2() throws FileNotFoundException, DocumentException {
         /*
         * Untuk Android 11 nanti file pdf tidak bisa diakses lewat file
        manager HP langsung
         * jadi harus konek lewat PC gara gara implementasi Scoped Storage.
         *
         * Kalau mau biar bisa di android 11 bisa pelajari sendiri tentang
        penggunaan MediaStorage
         * */
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = "Laporan " + headerLaporan + " - " + currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("Laporan " + headerLaporan + " \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        PdfPCell cellSupplier2 = new PdfPCell();
        cellSupplier2.setPaddingLeft(20);
        cellSupplier2.setPaddingBottom(10);
        cellSupplier2.setBorder(Rectangle.NO_BORDER);

        Paragraph tanggalMulai = new Paragraph(
                "Tanggal Mulai \n" + tglMulai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(tanggalMulai);
        tanggalMulai.setAlignment(Element.ALIGN_LEFT);
        tables.addCell(cellSupplier);

        Paragraph tanggalSelesai = new Paragraph(
                "Tanggal Selesai \n" + tglSelesai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier2.addElement(tanggalSelesai);
        tanggalSelesai.setAlignment(Element.ALIGN_RIGHT);
        tables.addCell(cellSupplier2);

        document.add(tables);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);
        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("Nama Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jenis Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        PdfPCell h5 = new PdfPCell(new Phrase("Pendapatan"));
        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
        h5.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
        tableHeader.addCell(h5);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        // masukan data pegawai jadi baris
        for (Laporan l : laporanList) {
            tableData.addCell(l.getNamaCustomer());
            tableData.addCell(l.getNamaMobil());
            tableData.addCell(String.valueOf(l.getJenisTransaksi()));
            tableData.addCell(String.valueOf(l.getJumlahTransaksi()));
            tableData.addCell(String.valueOf(l.getPendapatan()));
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_LEFT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "Laporan " + headerLaporan + " Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPdf3() throws FileNotFoundException, DocumentException {
         /*
         * Untuk Android 11 nanti file pdf tidak bisa diakses lewat file
        manager HP langsung
         * jadi harus konek lewat PC gara gara implementasi Scoped Storage.
         *
         * Kalau mau biar bisa di android 11 bisa pelajari sendiri tentang
        penggunaan MediaStorage
         * */
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = "Laporan " + headerLaporan + " - " + currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("Laporan " + headerLaporan + " \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        PdfPCell cellSupplier2 = new PdfPCell();
        cellSupplier2.setPaddingLeft(20);
        cellSupplier2.setPaddingBottom(10);
        cellSupplier2.setBorder(Rectangle.NO_BORDER);

        Paragraph tanggalMulai = new Paragraph(
                "Tanggal Mulai \n" + tglMulai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(tanggalMulai);
        tanggalMulai.setAlignment(Element.ALIGN_LEFT);
        tables.addCell(cellSupplier);

        Paragraph tanggalSelesai = new Paragraph(
                "Tanggal Selesai \n" + tglSelesai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier2.addElement(tanggalSelesai);
        tanggalSelesai.setAlignment(Element.ALIGN_RIGHT);
        tables.addCell(cellSupplier2);

        document.add(tables);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);
        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("ID Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        // masukan data pegawai jadi baris
        for (Laporan l : laporanList) {
            tableData.addCell(l.getIdDriver());
            tableData.addCell(l.getNamaDriver());
            tableData.addCell(String.valueOf(l.getTotalTransaksi()));
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_LEFT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "Laporan " + headerLaporan + " Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPdf4() throws FileNotFoundException, DocumentException {
         /*
         * Untuk Android 11 nanti file pdf tidak bisa diakses lewat file
        manager HP langsung
         * jadi harus konek lewat PC gara gara implementasi Scoped Storage.
         *
         * Kalau mau biar bisa di android 11 bisa pelajari sendiri tentang
        penggunaan MediaStorage
         * */
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = "Laporan " + headerLaporan + " - " + currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("Laporan " + headerLaporan + " \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        PdfPCell cellSupplier2 = new PdfPCell();
        cellSupplier2.setPaddingLeft(20);
        cellSupplier2.setPaddingBottom(10);
        cellSupplier2.setBorder(Rectangle.NO_BORDER);

        Paragraph tanggalMulai = new Paragraph(
                "Tanggal Mulai \n" + tglMulai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(tanggalMulai);
        tanggalMulai.setAlignment(Element.ALIGN_LEFT);
        tables.addCell(cellSupplier);

        Paragraph tanggalSelesai = new Paragraph(
                "Tanggal Selesai \n" + tglSelesai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier2.addElement(tanggalSelesai);
        tanggalSelesai.setAlignment(Element.ALIGN_RIGHT);
        tables.addCell(cellSupplier2);

        document.add(tables);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);
        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("ID Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Rerata Rating"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        // masukan data pegawai jadi baris
        for (Laporan l : laporanList) {
            tableData.addCell(l.getIdDriver());
            tableData.addCell(l.getNamaDriver());
            tableData.addCell(String.valueOf(l.getTotalTransaksi()));
            tableData.addCell(String.valueOf(l.getRerataRating()));
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_LEFT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "Laporan " + headerLaporan + " Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPdf5() throws FileNotFoundException, DocumentException {
         /*
         * Untuk Android 11 nanti file pdf tidak bisa diakses lewat file
        manager HP langsung
         * jadi harus konek lewat PC gara gara implementasi Scoped Storage.
         *
         * Kalau mau biar bisa di android 11 bisa pelajari sendiri tentang
        penggunaan MediaStorage
         * */
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = "Laporan " + headerLaporan + " - " + currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("Laporan " + headerLaporan + " \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        PdfPCell cellSupplier2 = new PdfPCell();
        cellSupplier2.setPaddingLeft(20);
        cellSupplier2.setPaddingBottom(10);
        cellSupplier2.setBorder(Rectangle.NO_BORDER);

        Paragraph tanggalMulai = new Paragraph(
                "Tanggal Mulai \n" + tglMulai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(tanggalMulai);
        tanggalMulai.setAlignment(Element.ALIGN_LEFT);
        tables.addCell(cellSupplier);

        Paragraph tanggalSelesai = new Paragraph(
                "Tanggal Selesai \n" + tglSelesai.getText() + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier2.addElement(tanggalSelesai);
        tanggalSelesai.setAlignment(Element.ALIGN_RIGHT);
        tables.addCell(cellSupplier2);

        document.add(tables);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);
        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("ID Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Customer"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        // masukan data pegawai jadi baris
        for (Laporan l : laporanList) {
            tableData.addCell(l.getIdCustomer());
            tableData.addCell(l.getNamaCustomer());
            tableData.addCell(String.valueOf(l.getTotalTransaksi()));
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_LEFT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "Laporan " + headerLaporan + " Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    private void previewPdf(File pdfFile) {
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(testIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Uri uri;
            uri = FileProvider.getUriForFile(this, getPackageName() +
                            ".provider",
                    pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uri, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.grantUriPermission(getPackageName(), uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(pdfIntent);
        }
    }

}