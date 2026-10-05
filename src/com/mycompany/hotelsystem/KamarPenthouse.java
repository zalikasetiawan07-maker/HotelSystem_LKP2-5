package com.mycompany.hotelsystem;

public class KamarPenthouse extends Kamar {
    private boolean adaHelipadAccess;

    public KamarPenthouse(String nomorKamar, String tipeKamar, double hargaPerMalam, boolean adaHelipadAccess) {
        super(nomorKamar, tipeKamar, hargaPerMalam);
        this.adaHelipadAccess = adaHelipadAccess;
    }

    public boolean isAdaHelipadAccess() {
        return adaHelipadAccess;
    }

    public void setAdaHelipadAccess(boolean adaHelipadAccess) {
        this.adaHelipadAccess = adaHelipadAccess;
    }

    @Override
    public void tampilkanInfo() {
        String helipad = adaHelipadAccess ? "Akses Helipad Pribadi" : "Standard VIP Access";
        System.out.printf("[Penthouse] No: %-5s | Tipe: %-8s | Harga: Rp %-9.0f | Akses: %s\n",
            getNomorKamar(), getTipeKamar(), getHargaPerMalam(), helipad);
    }

    @Override
    public void layananKamar() {
        System.out.println("-> Layanan: Lift Khusus, Private Chef, & Akses Helipad 24 Jam.");
    }
}