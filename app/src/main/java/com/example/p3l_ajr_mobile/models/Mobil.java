package com.example.p3l_ajr_mobile.models;

public class Mobil {

    private Long idMobil;
    private String namaMobil;
    private String jenisTransmisi;
    private String jenisBahanBakar;
    private String fasilitas;
    private Double hargaSewaMobil;
    private Integer statusKetersediaanMobil;
    private String gambarMobil;

    public Mobil(Long idMobil, String namaMobil, String jenisTransmisi, String jenisBahanBakar, String fasilitas, Double hargaSewaMobil, Integer statusKetersediaanMobil, String gambarMobil) {
        this.idMobil = idMobil;
        this.namaMobil = namaMobil;
        this.jenisTransmisi = jenisTransmisi;
        this.jenisBahanBakar = jenisBahanBakar;
        this.fasilitas = fasilitas;
        this.hargaSewaMobil = hargaSewaMobil;
        this.statusKetersediaanMobil = statusKetersediaanMobil;
        this.gambarMobil = gambarMobil;
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

    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public Double getHargaSewaMobil() {
        return hargaSewaMobil;
    }

    public void setHargaSewaMobil(Double hargaSewaMobil) {
        this.hargaSewaMobil = hargaSewaMobil;
    }

    public Integer getStatusKetersediaanMobil() {
        return statusKetersediaanMobil;
    }

    public void setStatusKetersediaanMobil(Integer statusKetersediaanMobil) {
        this.statusKetersediaanMobil = statusKetersediaanMobil;
    }

    public String getGambarMobil() {
        return gambarMobil;
    }

    public void setGambarMobil(String gambarMobil) {
        this.gambarMobil = gambarMobil;
    }
}
