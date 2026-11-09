package com.mycompany.hotelsystem;

public class Tamu {
    private String nik;
    private String nama;

    public Tamu(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }
}