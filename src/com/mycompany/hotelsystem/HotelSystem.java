package com.mycompany.hotelsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelSystem {

    public static void cariKamar(String noKamar, ArrayList<Kamar> daftarKamar) throws KamarTidakDitemukanException {
        System.out.println("\n[Pencarian] Mencari kamar dengan Nomor: " + noKamar);
        boolean ditemukan = false;
        
        for (int i = 0; i < daftarKamar.size(); i++) {
            if (daftarKamar.get(i).getNomorKamar().equalsIgnoreCase(noKamar)) {
                System.out.print("Ditemukan: ");
                daftarKamar.get(i).tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        
        if (!ditemukan) {
            throw new KamarTidakDitemukanException("Gagal: Kamar dengan nomor '" + noKamar + "' tidak ditemukan di sistem!");
        }
    }

    public static void simulasiLayanan(Kamar item) {
        System.out.print("Simulasi Layanan Kamar " + item.getNomorKamar() + " ");
        item.layananKamar();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Kamar> daftarKamar = new ArrayList<>();

        HashMap<String, String> dataTamu = new HashMap<>();

        boolean isRunning = true;

        try {
            daftarKamar.add(new KamarReguler("101", "Standard", 350000, true));
            daftarKamar.add(new KamarReguler("102", "Superior", 450000, false));
            daftarKamar.add(new KamarSuite("501", "Executive", 1200000, true));
            daftarKamar.add(new KamarSuite("502", "Presidential", 2500000, true));
            daftarKamar.add(new KamarPenthouse("PH1", "Royal", 5000000, true));
        } catch (Exception e) {
            System.out.println("Gagal menginisialisasi data awal: " + e.getMessage());
        }

        dataTamu.put("187101", "Budi Santoso");
        dataTamu.put("187102", "Siti Aminah");
        dataTamu.put("187103", "Ahmad Fauzi");

        System.out.println("=========================================================");
        System.out.println("  SISTEM MANAJEMEN HOTEL (MODUL 9 EXCEPTION & COLLECTION) ");
        System.out.println("=========================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Data Kamar Baru (ArrayList)");
            System.out.println("2. Lihat Daftar Kamar (ArrayList Demo)");
            System.out.println("3. Cari Kamar (Demo Custom Exception)");
            System.out.println("4. Manajemen Data Tamu (Demo HashMap)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu (1-5): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n--- Pilih Jenis Subclass Kamar ---");
                        System.out.println("1. Kamar Reguler");
                        System.out.println("2. Kamar Suite");
                        System.out.println("3. Kamar Penthouse");
                        System.out.print("Pilihan (1/2/3): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan No Kamar    : ");
                        String nomor = scanner.nextLine();
                        System.out.print("Masukkan Tipe Kamar  : ");
                        String tipe = scanner.nextLine();
                        System.out.print("Masukkan Harga/Malam : ");
                        double harga = scanner.nextDouble();
                        scanner.nextLine();

                        if (jenis == 1) {
                            System.out.print("Apakah ada sarapan? (true/false): ");
                            boolean sarapan = scanner.nextBoolean();
                            scanner.nextLine();
                            daftarKamar.add(new KamarReguler(nomor, tipe, harga, sarapan));
                            System.out.println("[Sukses] Kamar Reguler berhasil ditambahkan. (Total: " + daftarKamar.size() + ")");
                        } else if (jenis == 2) {
                            System.out.print("Akses Lounge VIP? (true/false): ");
                            boolean lounge = scanner.nextBoolean();
                            scanner.nextLine();
                            daftarKamar.add(new KamarSuite(nomor, tipe, harga, lounge));
                            System.out.println("[Sukses] Kamar Suite berhasil ditambahkan. (Total: " + daftarKamar.size() + ")");
                        } else if (jenis == 3) {
                            System.out.print("Akses Helipad Pribadi? (true/false): ");
                            boolean helipad = scanner.nextBoolean();
                            scanner.nextLine();
                            daftarKamar.add(new KamarPenthouse(nomor, tipe, harga, helipad));
                            System.out.println("[Sukses] Kamar Penthouse berhasil ditambahkan. (Total: " + daftarKamar.size() + ")");
                        } else {
                            System.out.println("[Error] Pilihan jenis kamar tidak valid.");
                        }
                    }

                    case 2 -> {
                        System.out.println("\n--- DAFTAR SELURUH KAMAR (ARRAYLIST DEMO) ---");
                        if (daftarKamar.isEmpty()) {
                            System.out.println("Belum ada data kamar yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarKamar.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarKamar.get(i).tampilkanInfo();
                                simulasiLayanan(daftarKamar.get(i));
                                
                                daftarKamar.get(i).hitungDendaKeterlambatan();

                                if (daftarKamar.get(i) instanceof DapatDipesan itemBisaDipesan) {
                                    itemBisaDipesan.prosesPemesanan();
                                }

                                if (daftarKamar.get(i) instanceof DapatDinilai itemBisaDinilai) {
                                    itemBisaDinilai.beriRating(5);
                                }
                                System.out.println();
                            }
                        }
                        System.out.println("Total Objek Tercipta: " + Kamar.getTotalKamarDibuat() + " (Ukuran ArrayList: " + daftarKamar.size() + ")");
                    }

                    case 3 -> {
                        System.out.println("\n--- FITUR CARI KAMAR (DEMO CUSTOM EXCEPTION) ---");
                        System.out.print("Masukkan Nomor Kamar yang Dicari: ");
                        String kataKunci = scanner.nextLine();

                        try {
                            cariKamar(kataKunci, daftarKamar);
                        } catch (KamarTidakDitemukanException e) {
                            System.out.println("[PERINGATAN EXCEPTION] " + e.getMessage());
                        } finally {
                            System.out.println("-> [FINALLY] Sesi pencarian kamar selesai dieksekusi.");
                        }
                    }

                    case 4 -> {
                        System.out.println("\n--- KELOLA DATA TAMU HOTEL (HASHMAP DEMO) ---");
                        System.out.println("1. Tambah Tamu Baru (.put)");
                        System.out.println("2. Cari Tamu via NIK/ID (.containsKey & .get)");
                        System.out.println("3. Tampilkan Semua Tamu (.keySet & .size)");
                        System.out.print("Pilih (1-3): ");
                        int menuTamu = scanner.nextInt();
                        scanner.nextLine();

                        if (menuTamu == 1) {
                            System.out.print("Masukkan NIK/ID Tamu (Key)   : ");
                            String nik = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (Value): ");
                            String nama = scanner.nextLine();
                            dataTamu.put(nik, nama);
                            System.out.println("[Sukses] Tamu berhasil terdaftar di HashMap!");
                        } else if (menuTamu == 2) {
                            System.out.print("Masukkan NIK/ID Tamu yang Dicari: ");
                            String searchNik = scanner.nextLine();
                            if (dataTamu.containsKey(searchNik)) {
                                System.out.println("[Ditemukan!] Nama Tamu: " + dataTamu.get(searchNik));
                            } else {
                                System.out.println("[Peringatan] Tamu dengan NIK '" + searchNik + "' tidak terdaftar.");
                            }
                        } else if (menuTamu == 3) {
                            System.out.println("\nTotal Tamu Terdaftar: " + dataTamu.size());
                            for (String keyNik : dataTamu.keySet()) {
                                System.out.println("- NIK: " + keyNik + " | Nama: " + dataTamu.get(keyNik));
                            }
                        } else {
                            System.out.println("[Error] Pilihan menu tamu tidak valid.");
                        }
                    }

                    case 5 -> {
                        System.out.println("\nTerima kasih telah menggunakan Sistem Reservasi Hotel!");
                        isRunning = false;
                    }

                    default -> System.out.println("[Error] Pilihan menu tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR INPUT] Anda harus memasukkan format angka valid!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kesalahan: " + e.getMessage());
            }
        }

        scanner.close();
    }
}