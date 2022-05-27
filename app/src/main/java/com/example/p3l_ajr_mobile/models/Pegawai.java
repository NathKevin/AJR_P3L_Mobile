package com.example.p3l_ajr_mobile.models;

public class Pegawai {
    private Long idPegawai;
    private Long idRole;
    private String namaPegawai;
    private String alamatPegawai;
    private String tanggalLahirPegawai;
    private String jenisKelaminPegawai;
    private String email;
    private String password;
    private String noTelpPegawai;
    private String fotoPegawai;
    private Integer isActive;
    private String api_token;

    public Pegawai(Long idPegawai, Long idRole, String namaPegawai, String alamatPegawai, String tanggalLahirPegawai, String jenisKelaminPegawai,
                   String email, String password, String noTelpPegawai, String fotoPegawai, Integer isActive, String api_token) {
        this.idPegawai = idPegawai;
        this.idRole = idRole;
        this.namaPegawai = namaPegawai;
        this.alamatPegawai = alamatPegawai;
        this.tanggalLahirPegawai = tanggalLahirPegawai;
        this.jenisKelaminPegawai = jenisKelaminPegawai;
        this.email = email;
        this.password = password;
        this.noTelpPegawai = noTelpPegawai;
        this.fotoPegawai = fotoPegawai;
        this.isActive = isActive;
        this.api_token = api_token;
    }

    public Long getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Long idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
    }

    public String getJenisKelaminPegawai() {
        return jenisKelaminPegawai;
    }

    public void setJenisKelaminPegawai(String jenisKelaminPegawai) {
        this.jenisKelaminPegawai = jenisKelaminPegawai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelpPegawai() {
        return noTelpPegawai;
    }

    public void setNoTelpPegawai(String noTelpPegawai) {
        this.noTelpPegawai = noTelpPegawai;
    }

    public String getFotoPegawai() {
        return fotoPegawai;
    }

    public void setFotoPegawai(String fotoPegawai) {
        this.fotoPegawai = fotoPegawai;
    }

    public Integer getActive() {
        return isActive;
    }

    public void setActive(Integer active) {
        isActive = active;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getTanggalLahirPegawai() {
        return tanggalLahirPegawai;
    }

    public void setTanggalLahirPegawai(String tanggalLahirPegawai) {
        this.tanggalLahirPegawai = tanggalLahirPegawai;
    }
}
