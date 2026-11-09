package com.mycompany.hotelsystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
    private String namaHotel;

    private RuangLobby lobbyUtama;

    private ArrayList<Kamar> daftarKamar;
    private HashMap<String, Tamu> dataTamu;

    public Hotel(String namaHotel) {
        this.namaHotel = namaHotel;

        this.lobbyUtama = new RuangLobby("Lobby Utama & Executive Lounge", 100);

        this.daftarKamar = new ArrayList<>();
        this.dataTamu = new HashMap<>();
    }

    public void bukaHotel() {
        System.out.println("\n*** Menginisialisasi Sistem " + this.namaHotel + " ***");
        lobbyUtama.bukaLobby();
    }

    public void tambahKamar(Kamar k) {
        daftarKamar.add(k);
        System.out.println("[Sukses] Kamar " + k.getNomorKamar() + " ditambahkan. (Total Kamar: " + daftarKamar.size() + ")");
    }

    public void tambahTamu(Tamu t) {
        dataTamu.put(t.getNik(), t);
        System.out.println("[Sukses] Tamu " + t.getNama() + " berhasil terdaftar!");
    }

    public void lihatSemuaKamar() {
        if (daftarKamar.isEmpty()) {
            System.out.println("Belum ada kamar yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarKamar.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKamar.get(i).tampilkanInfo();
        }
    }

    public void cariKamar(String noKamar) throws KamarTidakDitemukanException {
        System.out.println("\n--- [Pencarian Kamar: " + noKamar + "] ---");
        boolean ditemukan = false;
        for (Kamar k : daftarKamar) {
            if (k.getNomorKamar().equalsIgnoreCase(noKamar)) {
                System.out.print("Ditemukan: ");
                k.tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            throw new KamarTidakDitemukanException("Gagal: Kamar dengan nomor '" + noKamar + "' tidak terdaftar di sistem!");
        }
    }

    public void cariTamu(String nik) {
        if (dataTamu.containsKey(nik)) {
            System.out.println("Ditemukan! Nama Tamu: " + dataTamu.get(nik).getNama());
        } else {
            System.out.println("Anggota/Tamu dengan NIK " + nik + " tidak terdaftar.");
        }
    }

    public void lihatSemuaTamu() {
        System.out.println("\nTotal Tamu Terdaftar: " + dataTamu.size());
        for (Tamu t : dataTamu.values()) {
            System.out.println("- NIK: " + t.getNik() + " | Nama: " + t.getNama());
        }
    }

    public Tamu getTamu(String nik) {
        return dataTamu.get(nik);
    }

    public Kamar getKamarIndex(int index) {
        return daftarKamar.get(index);
    }

    public int getJumlahKamar() {
        return daftarKamar.size();
    }
}