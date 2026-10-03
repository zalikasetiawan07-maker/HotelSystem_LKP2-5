package com.mycompany.hotelsystem;

public class Kamar {
    private String nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;

    private static int totalKamarDibuat = 0;

    public Kamar(String nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        setHargaPerMalam(hargaPerMalam);
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
            System.out.println("[Sistem] Harga tidak valid! Diset ke default Rp 200.000.");
            this.hargaPerMalam = 200000;
        }
    }

    public static int getTotalKamarDibuat() {
        return totalKamarDibuat;
    }

    public void tampilkanInfo() {
        System.out.printf("No Kamar: %-6s | Tipe: %-10s | Harga/Malam: Rp %-10.2f", 
            this.nomorKamar, this.tipeKamar, this.hargaPerMalam);
    }

    public void layananKamar() {
        System.out.println("-> Layanan: Pembersihan kamar standar setiap hari.");
    }
}