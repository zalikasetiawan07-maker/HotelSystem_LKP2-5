package com.mycompany.hotelsystem;

public class KamarReguler extends Kamar {
    private boolean adaSarapan;

    public KamarReguler(String nomorKamar, String tipeKamar, double hargaPerMalam, boolean adaSarapan) {
        super(nomorKamar, tipeKamar, hargaPerMalam);
        this.adaSarapan = adaSarapan;
    }

    public boolean isAdaSarapan() { 
        return adaSarapan; 
    }
    
    public void setAdaSarapan(boolean adaSarapan) { 
        this.adaSarapan = adaSarapan; 
    }

    @Override
    public void tampilkanInfo() {
        String sarapan = adaSarapan ? "Termasuk Sarapan" : "Tanpa Sarapan";
        System.out.printf("[Reguler] No: %-5s | Tipe: %-8s | Harga: Rp %-9.0f | Status: %s\n",
            getNomorKamar(), getTipeKamar(), getHargaPerMalam(), sarapan);
    }

    @Override
    public void layananKamar() {
        System.out.println("-> Layanan: Wifi gratis & akses kolam renang umum.");
    }
}