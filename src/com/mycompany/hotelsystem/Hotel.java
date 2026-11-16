package com.mycompany.hotelsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_hotel.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(this.daftarKamar);
            mesinPembeku.writeObject(this.dataTamu);

            mesinPembeku.close();
            fileKeluar.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data kamar & tamu berhasil disimpan (Serialization) ke data_hotel.dat!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_hotel.dat");
            if (!cekFile.exists()) {
                System.out.println("-> [SYSTEM LOG] File penyimpanan belum ditemukan. Memuat data awal default...");
                return;
            }

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);

            this.daftarKamar = (ArrayList<Kamar>) mesinPembangkit.readObject();
            this.dataTamu = (HashMap<String, Tamu>) mesinPembangkit.readObject();

            Kamar.setTotalKamarDibuat(this.daftarKamar.size());

            mesinPembangkit.close();
            fileMasuk.close();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang (Deserialization) dari data_hotel.dat!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data dari file: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        System.out.println("\n--- RIWAYAT LOG TRANSAKSI (log_transaksi.txt) ---");
        try {
            File fileLog = new File("log_transaksi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada catatan log transaksi di file.");
                return;
            }

            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log: " + e.getMessage());
        }
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

    public int getJumlahTamu() {
        return dataTamu.size();
    }
}