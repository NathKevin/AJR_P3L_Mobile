package com.example.p3l_ajr_mobile.models;

public class Promo {
    private Long idPromo;
    private String kode;
    private String jenisPromo;
    private Double besarPromo;
    private String keterangan;

    public Promo(Long idPromo, String kode, String jenisPromo, Double besarPromo, String keterangan) {
        this.idPromo = idPromo;
        this.kode = kode;
        this.jenisPromo = jenisPromo;
        this.besarPromo = besarPromo;
        this.keterangan = keterangan;
    }

    public Long getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Long idPromo) {
        this.idPromo = idPromo;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJenisPromo() {
        return jenisPromo;
    }

    public void setJenisPromo(String jenisPromo) {
        this.jenisPromo = jenisPromo;
    }

    public Double getBesarPromo() {
        return besarPromo;
    }

    public void setBesarPromo(Double besarPromo) {
        this.besarPromo = besarPromo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
