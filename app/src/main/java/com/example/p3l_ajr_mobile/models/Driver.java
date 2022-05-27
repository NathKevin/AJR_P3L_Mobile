package com.example.p3l_ajr_mobile.models;

public class Driver {
    private String idDriver;
    private String namaDriver;
    private String alamatDriver;
    private String tanggalLahirDriver;
    private String jenisKelaminDriver;
    private String email;
    private String password;
    private String noTelpDriver;
    private String bahasa;
    private Integer statusKetersediaanDriver;
    private Double hargaSewaDriver;
    private Double rerataRating;
    private String fotoDriver;
    private String fotocopySIM;
    private String bebasNAPZA;
    private String kesehatanJiwa;
    private String kesehatanJasmani;
    private String SKCK;
    private Integer isActive;
    private String api_token;
    private String statusBerkas;
    private String oldPassword;

    public Driver(String idDriver, String namaDriver, String alamatDriver, String tanggalLahirDriver, String jenisKelaminDriver,
                  String email, String password, String noTelpDriver, String bahasa, Integer statusKetersediaanDriver, Double hargaSewaDriver,
                  Double rerataRating, String fotoDriver, String fotocopySIM, String bebasNAPZA, String kesehatanJiwa, String kesehatanJasmani,
                  String SKCK, Integer isActive, String api_token, String statusBerkas) {
        this.idDriver = idDriver;
        this.namaDriver = namaDriver;
        this.alamatDriver = alamatDriver;
        this.tanggalLahirDriver = tanggalLahirDriver;
        this.jenisKelaminDriver = jenisKelaminDriver;
        this.email = email;
        this.password = password;
        this.noTelpDriver = noTelpDriver;
        this.bahasa = bahasa;
        this.statusKetersediaanDriver = statusKetersediaanDriver;
        this.hargaSewaDriver = hargaSewaDriver;
        this.rerataRating = rerataRating;
        this.fotoDriver = fotoDriver;
        this.fotocopySIM = fotocopySIM;
        this.bebasNAPZA = bebasNAPZA;
        this.kesehatanJiwa = kesehatanJiwa;
        this.kesehatanJasmani = kesehatanJasmani;
        this.SKCK = SKCK;
        this.isActive = isActive;
        this.api_token = api_token;
        this.statusBerkas = statusBerkas;
    }

    public Driver(String namaDriver, String alamatDriver, String tanggalLahirDriver, String jenisKelaminDriver, String noTelpDriver, String bahasa) {
        this.namaDriver = namaDriver;
        this.alamatDriver = alamatDriver;
        this.tanggalLahirDriver = tanggalLahirDriver;
        this.jenisKelaminDriver = jenisKelaminDriver;
        this.noTelpDriver = noTelpDriver;
        this.bahasa = bahasa;
    }

    public Driver(Integer isActive) {
        this.isActive = isActive;
    }

    public Driver(String value) {
        this.email = value;
    }

    public Driver(String password, String oldPassword){
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public Driver(Double rerataRating) {
        this.rerataRating = rerataRating;
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

    public String getAlamatDriver() {
        return alamatDriver;
    }

    public void setAlamatDriver(String alamatDriver) {
        this.alamatDriver = alamatDriver;
    }

    public String getTanggalLahirDriver() {
        return tanggalLahirDriver;
    }

    public void setTanggalLahirDriver(String tanggalLahirDriver) {
        this.tanggalLahirDriver = tanggalLahirDriver;
    }

    public String getJenisKelaminDriver() {
        return jenisKelaminDriver;
    }

    public void setJenisKelaminDriver(String jenisKelaminDriver) {
        this.jenisKelaminDriver = jenisKelaminDriver;
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

    public String getNoTelpDriver() {
        return noTelpDriver;
    }

    public void setNoTelpDriver(String noTelpDriver) {
        this.noTelpDriver = noTelpDriver;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public Integer getStatusKetersediaanDriver() {
        return statusKetersediaanDriver;
    }

    public void setStatusKetersediaanDriver(Integer statusKetersediaanDriver) {
        this.statusKetersediaanDriver = statusKetersediaanDriver;
    }

    public Double getHargaSewaDriver() {
        return hargaSewaDriver;
    }

    public void setHargaSewaDriver(Double hargaSewaDriver) {
        this.hargaSewaDriver = hargaSewaDriver;
    }

    public Double getRerataRating() {
        return rerataRating;
    }

    public void setRerataRating(Double rerataRating) {
        this.rerataRating = rerataRating;
    }

    public String getFotoDriver() {
        return fotoDriver;
    }

    public void setFotoDriver(String fotoDriver) {
        this.fotoDriver = fotoDriver;
    }

    public String getFotocopySIM() {
        return fotocopySIM;
    }

    public void setFotocopySIM(String fotocopySIM) {
        this.fotocopySIM = fotocopySIM;
    }

    public String getBebasNAPZA() {
        return bebasNAPZA;
    }

    public void setBebasNAPZA(String bebasNAPZA) {
        this.bebasNAPZA = bebasNAPZA;
    }

    public String getKesehatanJiwa() {
        return kesehatanJiwa;
    }

    public void setKesehatanJiwa(String kesehatanJiwa) {
        this.kesehatanJiwa = kesehatanJiwa;
    }

    public String getKesehatanJasmani() {
        return kesehatanJasmani;
    }

    public void setKesehatanJasmani(String kesehatanJasmani) {
        this.kesehatanJasmani = kesehatanJasmani;
    }

    public String getSKCK() {
        return SKCK;
    }

    public void setSKCK(String SKCK) {
        this.SKCK = SKCK;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
