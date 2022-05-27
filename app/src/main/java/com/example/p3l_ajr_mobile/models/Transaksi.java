package com.example.p3l_ajr_mobile.models;

import java.io.Serializable;

public class Transaksi implements Serializable {
    private String idTransaksi;
    private Long idPegawai;
    private String idCustomer;
    private Long idPembayaran;
    private String idDriver;
    private String tanggalTransaksi;
    private String tanggalWaktuSewa;
    private String tanggalWaktuSelesai;
    private String tanggalWaktuKembali;
    private String statusTransaksi;
    private Integer rateDriver;
    private String performaDriver;

    //JOIN MOBIL
    private Long idMobil;
    private String namaMobil;
    private String platNomor;
    private Long hargaSewaMobil;
    private String gambarMobil;

    //JOIN PROMO
    private Long idPromo;
    private String jenisPromo;
    private Float besarPromo;

    //JOIN PEMBAYARAN
    private String metodePembayaran;
    private Long totalPromo;
    private Long totalBiayaMobil;
    private Long totalBiayaDriver;
    private Long dendaPeminjaman;
    private Long totalBiaya;
    private Integer statusPembayaran;

    //JOIN DRIVER
    private String namaDriver;
    private Long hargaSewaDriver;
    private String fotoDriver;

    //JOIN PEGAWAI
    private String namaPegawai;

    //JOIN USER
    private String namaCustomer;

    public Transaksi(String idTransaksi, Long idPegawai, String idCustomer, Long idPembayaran, String idDriver, String tanggalTransaksi,
                     String tanggalWaktuSewa, String tanggalWaktuSelesai, String tanggalWaktuKembali, String statusTransaksi, Integer rateDriver,
                     String performaDriver, Long idMobil, String namaMobil, String platNomor, Long hargaSewaMobil, String gambarMobil, Long idPromo,
                     String jenisPromo, Float besarPromo, String metodePembayaran, Long totalPromo, Long totalBiayaMobil, Long totalBiayaDriver,
                     Long dendaPeminjaman, Long totalBiaya, Integer statusPembayaran, String namaDriver, Long hargaSewaDriver, String fotoDriver, String namaPegawai) {
        this.idTransaksi = idTransaksi;
        this.idPegawai = idPegawai;
        this.idCustomer = idCustomer;
        this.idPembayaran = idPembayaran;
        this.idDriver = idDriver;
        this.tanggalTransaksi = tanggalTransaksi;
        this.tanggalWaktuSewa = tanggalWaktuSewa;
        this.tanggalWaktuSelesai = tanggalWaktuSelesai;
        this.tanggalWaktuKembali = tanggalWaktuKembali;
        this.statusTransaksi = statusTransaksi;
        this.rateDriver = rateDriver;
        this.performaDriver = performaDriver;
        this.idMobil = idMobil;
        this.namaMobil = namaMobil;
        this.platNomor = platNomor;
        this.hargaSewaMobil = hargaSewaMobil;
        this.gambarMobil = gambarMobil;
        this.idPromo = idPromo;
        this.jenisPromo = jenisPromo;
        this.besarPromo = besarPromo;
        this.metodePembayaran = metodePembayaran;
        this.totalPromo = totalPromo;
        this.totalBiayaMobil = totalBiayaMobil;
        this.totalBiayaDriver = totalBiayaDriver;
        this.dendaPeminjaman = dendaPeminjaman;
        this.totalBiaya = totalBiaya;
        this.statusPembayaran = statusPembayaran;
        this.namaDriver = namaDriver;
        this.hargaSewaDriver = hargaSewaDriver;
        this.fotoDriver = fotoDriver;
        this.namaPegawai = namaPegawai;
    }

    public Transaksi(Integer rateDriver, String performaDriver) {
        this.rateDriver = rateDriver;
        this.performaDriver = performaDriver;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Long getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Long idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getTanggalWaktuSewa() {
        return tanggalWaktuSewa;
    }

    public void setTanggalWaktuSewa(String tanggalWaktuSewa) {
        this.tanggalWaktuSewa = tanggalWaktuSewa;
    }

    public String getTanggalWaktuSelesai() {
        return tanggalWaktuSelesai;
    }

    public void setTanggalWaktuSelesai(String tanggalWaktuSelesai) {
        this.tanggalWaktuSelesai = tanggalWaktuSelesai;
    }

    public String getTanggalWaktuKembali() {
        return tanggalWaktuKembali;
    }

    public void setTanggalWaktuKembali(String tanggalWaktuKembali) {
        this.tanggalWaktuKembali = tanggalWaktuKembali;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public Integer getRateDriver() {
        return rateDriver;
    }

    public void setRateDriver(Integer rateDriver) {
        this.rateDriver = rateDriver;
    }

    public String getPerformaDriver() {
        return performaDriver;
    }

    public void setPerformaDriver(String performaDriver) {
        this.performaDriver = performaDriver;
    }

    public Long getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(Long idMobil) {
        this.idMobil = idMobil;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public Long getHargaSewaMobil() {
        return hargaSewaMobil;
    }

    public void setHargaSewaMobil(Long hargaSewaMobil) {
        this.hargaSewaMobil = hargaSewaMobil;
    }

    public String getGambarMobil() {
        return gambarMobil;
    }

    public void setGambarMobil(String gambarMobil) {
        this.gambarMobil = gambarMobil;
    }

    public Long getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Long idPromo) {
        this.idPromo = idPromo;
    }

    public String getJenisPromo() {
        return jenisPromo;
    }

    public void setJenisPromo(String jenisPromo) {
        this.jenisPromo = jenisPromo;
    }

    public Float getBesarPromo() {
        return besarPromo;
    }

    public void setBesarPromo(Float besarPromo) {
        this.besarPromo = besarPromo;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public Long getTotalPromo() {
        return totalPromo;
    }

    public void setTotalPromo(Long totalPromo) {
        this.totalPromo = totalPromo;
    }

    public Long getTotalBiayaMobil() {
        return totalBiayaMobil;
    }

    public void setTotalBiayaMobil(Long totalBiayaMobil) {
        this.totalBiayaMobil = totalBiayaMobil;
    }

    public Long getTotalBiayaDriver() {
        return totalBiayaDriver;
    }

    public void setTotalBiayaDriver(Long totalBiayaDriver) {
        this.totalBiayaDriver = totalBiayaDriver;
    }

    public Long getDendaPeminjaman() {
        return dendaPeminjaman;
    }

    public void setDendaPeminjaman(Long dendaPeminjaman) {
        this.dendaPeminjaman = dendaPeminjaman;
    }

    public Long getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(Long totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public Integer getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(Integer statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public Long getHargaSewaDriver() {
        return hargaSewaDriver;
    }

    public void setHargaSewaDriver(Long hargaSewaDriver) {
        this.hargaSewaDriver = hargaSewaDriver;
    }

    public String getFotoDriver() {
        return fotoDriver;
    }

    public void setFotoDriver(String fotoDriver) {
        this.fotoDriver = fotoDriver;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }
}
