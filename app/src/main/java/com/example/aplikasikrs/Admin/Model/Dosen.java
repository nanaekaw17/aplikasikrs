package com.example.aplikasikrs.Admin.Model;

public class Dosen {
    private String nidn;
    private String namaDosen;
    private String gelar;
    private String email;
    private String alamat;
    private int foto;

    public Dosen(String nidn, String namaDosen, String gelar, String email, String alamat, int foto) {
        this.nidn = nidn;
        this.namaDosen = namaDosen;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;
    }
    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
