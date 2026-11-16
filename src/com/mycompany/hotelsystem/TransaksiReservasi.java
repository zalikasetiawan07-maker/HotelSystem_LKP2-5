package com.mycompany.hotelsystem;

import java.io.FileWriter;
import java.io.IOException;

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

        try {
            FileWriter pipaTulis = new FileWriter("log_transaksi.txt", true);
            String log = "Tamu: " + tamu.getNama() + " (" + tamu.getNik() + ") | Kamar: " 
                       + kamar.getNomorKamar() + " (" + kamar.getTipeKamar() + ") | Harga: Rp " 
                       + String.format("%.0f", kamar.getHargaPerMalam()) + "\n";
            pipaTulis.write(log);
            pipaTulis.close();
            System.out.println("-> [FILE I/O LOG] Riwayat transaksi berhasil ditulis ke log_transaksi.txt");
        } catch (IOException e) {
            System.out.println("-> [PERINGATAN] Gagal menulis log transaksi ke file: " + e.getMessage());
        }
    }
}