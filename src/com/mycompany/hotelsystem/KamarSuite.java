package com.mycompany.hotelsystem;

public class KamarSuite extends Kamar {
    private boolean adaAksesLoungeVIP;

    public KamarSuite(String nomorKamar, String tipeKamar, double hargaPerMalam, boolean adaAksesLoungeVIP) {
        super(nomorKamar, tipeKamar, hargaPerMalam);
        this.adaAksesLoungeVIP = adaAksesLoungeVIP;
    }

    public boolean isAdaAksesLoungeVIP() { 
        return adaAksesLoungeVIP; 
    }
    
    public void setAdaAksesLoungeVIP(boolean adaAksesLoungeVIP) { 
        this.adaAksesLoungeVIP = adaAksesLoungeVIP; 
    }

    @Override
    public void tampilkanInfo() {
        String vip = adaAksesLoungeVIP ? "Akses Lounge VIP Active" : "No Lounge Access";
        System.out.printf("[Suite  ] No: %-5s | Tipe: %-8s | Harga: Rp %-9.0f | VIP: %s\n",
            getNomorKamar(), getTipeKamar(), getHargaPerMalam(), vip);
    }

    @Override
    public void layananKamar() {
        System.out.println("-> Layanan: Butler 24 Jam, Jacuzzi Pribadi, & Akses Lounge VIP.");
    }
}