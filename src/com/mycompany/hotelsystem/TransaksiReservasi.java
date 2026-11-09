package com.mycompany.hotelsystem;

public class TransaksiReservasi {
    public static void prosesReservasi(Tamu tamu, Kamar kamar) {
        System.out.println("\n=================================================");
        System.out.println("           STRUK TRANSAKSI RESERVASI             ");
        System.out.println("=================================================");
        System.out.println("Peminjam/Tamu: " + tamu.getNama() + " (NIK: " + tamu.getNik() + ")");
        System.out.println("Kamar Dipesan: " + kamar.getNomorKamar() + " - " + kamar.getTipeKamar());
        System.out.print("Status Denda : ");
        kamar.hitungDendaKeterlambatan();
        System.out.print("Fasilitas    : ");
        kamar.layananKamar();
        
        if (kamar instanceof DapatDipesan) {
            ((DapatDipesan) kamar).prosesPemesanan();
        }
        System.out.println("Status       : RESERVASI BERHASIL DICATAT DI SISTEM.");
        System.out.println("=================================================");
    }
}