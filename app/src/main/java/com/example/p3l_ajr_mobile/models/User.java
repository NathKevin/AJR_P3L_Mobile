package com.example.p3l_ajr_mobile.models;

public class User {
    private String idCustomer;
    private String namaCustomer;
    private String alamatCustomer;
    private String tanggalLahirCustomer;
    private String jenisKelaminCustomer;
    private String kategoriCustomer;
    private String email;
    private String password;
    private String noTelpCustomer;
    private String KTP;
    private String SIM;
    private String KP;
    private String ratingAJR;
    private String performaAJR;
    private String api_token;
    private String statusBerkas;
    private String waiting;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String idCustomer, String namaCustomer, String alamatCustomer, String tanggalLahirCustomer,
                String jenisKelaminCustomer, String kategoriCustomer, String email, String password,
                String noTelpCustomer, String KTP, String SIM, String KP, String ratingAJR,
                String performaAJR, String api_token, String statusBerkas, String waiting) {
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.alamatCustomer = alamatCustomer;
        this.tanggalLahirCustomer = tanggalLahirCustomer;
        this.jenisKelaminCustomer = jenisKelaminCustomer;
        this.kategoriCustomer = kategoriCustomer;
        this.email = email;
        this.password = password;
        this.noTelpCustomer = noTelpCustomer;
        this.KTP = KTP;
        this.SIM = SIM;
        this.KP = KP;
        this.ratingAJR = ratingAJR;
        this.performaAJR = performaAJR;
        this.api_token = api_token;
        this.statusBerkas = statusBerkas;
        this.waiting = waiting;
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

    public String getAlamatCustomer() {
        return alamatCustomer;
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer = alamatCustomer;
    }

    public String getTanggalLahirCustomer() {
        return tanggalLahirCustomer;
    }

    public void setTanggalLahirCustomer(String tanggalLahirCustomer) {
        this.tanggalLahirCustomer = tanggalLahirCustomer;
    }

    public String getJenisKelaminCustomer() {
        return jenisKelaminCustomer;
    }

    public void setJenisKelaminCustomer(String jenisKelaminCustomer) {
        this.jenisKelaminCustomer = jenisKelaminCustomer;
    }

    public String getKategoriCustomer() {
        return kategoriCustomer;
    }

    public void setKategoriCustomer(String kategoriCustomer) {
        this.kategoriCustomer = kategoriCustomer;
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

    public String getNoTelpCustomer() {
        return noTelpCustomer;
    }

    public void setNoTelpCustomer(String noTelpCustomer) {
        this.noTelpCustomer = noTelpCustomer;
    }

    public String getKTP() {
        return KTP;
    }

    public void setKTP(String KTP) {
        this.KTP = KTP;
    }

    public String getSIM() {
        return SIM;
    }

    public void setSIM(String SIM) {
        this.SIM = SIM;
    }

    public String getKP() {
        return KP;
    }

    public void setKP(String KP) {
        this.KP = KP;
    }

    public String getRatingAJR() {
        return ratingAJR;
    }

    public void setRatingAJR(String ratingAJR) {
        this.ratingAJR = ratingAJR;
    }

    public String getPerformaAJR() {
        return performaAJR;
    }

    public void setPerformaAJR(String performaAJR) {
        this.performaAJR = performaAJR;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getStatusBerkas() {
        return statusBerkas;
    }

    public void setStatusBerkas(String statusBerkas) {
        this.statusBerkas = statusBerkas;
    }

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }
}
