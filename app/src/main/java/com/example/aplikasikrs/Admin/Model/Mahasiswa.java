package com.example.aplikasikrs.Admin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {

    @SerializedName("nama")
    @Expose
    private String namaMhs;

    @SerializedName("email")
    @Expose
    private String emailMhs;

    @SerializedName("alamat")
    @Expose
    private String alamatMhs;

    @SerializedName("foto")
    @Expose
    private String fotoMhs;

    @SerializedName("idMhs")
    @Expose
    private String idMhs;

    @SerializedName("nim_progmob")
    @Expose
    private String nim;

//public class Mahasiswa {
//    private String nim;
//    private String nama;
//    private String emailMhs;
//    private String alamatMhs;
//    private int fotoMhs;

    public Mahasiswa(String nim, String nama, String emailMhs, String alamatMhs, String fotoMhs) {
        this.nim = nim;
        this.namaMhs = nama;
        this.emailMhs = emailMhs;
        this.alamatMhs = alamatMhs;
        this.fotoMhs = fotoMhs;
        this.idMhs = idMhs;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return namaMhs;
    }

    public void setNama(String nama) {
        this.namaMhs = nama;
    }

    public String getEmailMhs() {
        return emailMhs;
    }

    public void setEmailMhs(String emailMhs) {
        this.emailMhs = emailMhs;
    }

    public String getAlamatMhs() {
        return alamatMhs;
    }

    public void setAlamatMhs(String alamatMhs) {
        this.alamatMhs = alamatMhs;
    }

    public String getFotoMhs() {
        return fotoMhs;
    }

    public void setFotoMhs(String fotoMhs) {
        this.fotoMhs = fotoMhs;
    }

    public String getIdMhs() {
        return idMhs;
    }

    public void setId(String id) {
        this.idMhs = id;
    }
}
