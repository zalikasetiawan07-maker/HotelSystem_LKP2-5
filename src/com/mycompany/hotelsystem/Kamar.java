package com.mycompany.hotelsystem;

import java.io.Serializable;

public abstract class Kamar implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private static int totalKamarDibuat = 0;

    public Kamar(String nomorKamar, String tipeKamar, double hargaPerMalam) throws Exception {
        if (nomorKamar == null || nomorKamar.trim().isEmpty()) {
            throw new Exception("Nomor kamar tidak boleh kosong!");
        }
        if (hargaPerMalam <= 0) {
            throw new Exception("Harga per malam harus lebih besar dari Rp 0!");
        }
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        totalKamarDibuat++;
    }

    public String getNomorKamar() {
        return this.nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getTipeKamar() {
        return this.tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public double getHargaPerMalam() {
        return this.hargaPerMalam;
    }

    public void setHargaPerMalam(double hargaPerMalam) {
        if (hargaPerMalam > 0) {
            this.hargaPerMalam = hargaPerMalam;
        } else {
            System.out.println("[Sistem] Harga tidak valid! Tetap menggunakan harga lama.");
        }
    }

    public static int getTotalKamarDibuat() {
        return totalKamarDibuat;
    }

    public static void setTotalKamarDibuat(int total) {
        totalKamarDibuat = total;
    }

    public void tampilkanInfo() {
        System.out.printf("No: %-5s | Tipe: %-8s | Harga: Rp %-9.0f\n", 
            this.nomorKamar, this.tipeKamar, this.hargaPerMalam);
    }

    public void layananKamar() {
        System.out.println("-> Layanan standar kamar hotel.");
    }

    public abstract void hitungDendaKeterlambatan();
}