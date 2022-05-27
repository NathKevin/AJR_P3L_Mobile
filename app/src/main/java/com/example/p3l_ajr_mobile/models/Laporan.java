package com.example.p3l_ajr_mobile.models;

public class Laporan {
    private String idCustomer;
    private String namaCustomer;
    private String idDriver;
    private String namaDriver;
    private Integer totalTransaksi;

    private Integer idMobil;
    private String tipeMobil;
    private String namaMobil;
    private Integer jumlahPeminjaman;
    private Integer pendapatan;

    private String jenisTransaksi;
    private Integer jumlahTransaksi;

    private Float rerataRating;

    public Laporan(String id, String nama, Integer totalTransaksi, Integer temp) {
        if(temp == 1){
            this.idCustomer = id;
            this.namaCustomer = nama;
            this.totalTransaksi = totalTransaksi;
        }else{
            this.idDriver = id;
            this.namaDriver = nama;
            this.totalTransaksi = totalTransaksi;
        }
    }

    public Laporan(Integer idMobil, String tipeMobil, String namaMobil, Integer jumlahPeminjaman, Integer pendapatan) {
        this.idMobil = idMobil;
        this.tipeMobil = tipeMobil;
        this.namaMobil = namaMobil;
        this.jumlahPeminjaman = jumlahPeminjaman;
        this.pendapatan = pendapatan;
    }

    public Laporan(String namaCustomer, String namaMobil, Integer pendapatan, String jenisTransaksi, Integer jumlahTransaksi) {
        this.namaCustomer = namaCustomer;
        this.namaMobil = namaMobil;
        this.pendapatan = pendapatan;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public Laporan(String idDriver, String namaDriver, Integer totalTransaksi, Float rerataRating) {
        this.idDriver = idDriver;
        this.namaDriver = namaDriver;
        this.totalTransaksi = totalTransaksi;
        this.rerataRating = rerataRating;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public Integer getTotalTransaksi() {
        return totalTransaksi;
    }

    public void setTotalTransaksi(Integer totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

    public Integer getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(Integer idMobil) {
        this.idMobil = idMobil;
    }

    public String getTipeMobil() {
        return tipeMobil;
    }

    public void setTipeMobil(String tipeMobil) {
        this.tipeMobil = tipeMobil;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public Integer getJumlahPeminjaman() {
        return jumlahPeminjaman;
    }

    public void setJumlahPeminjaman(Integer jumlahPeminjaman) {
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    public Integer getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(Integer pendapatan) {
        this.pendapatan = pendapatan;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public Integer getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(Integer jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public Float getRerataRating() {
        return rerataRating;
    }

    public void setRerataRating(Float rerataRating) {
        this.rerataRating = rerataRating;
    }
}
