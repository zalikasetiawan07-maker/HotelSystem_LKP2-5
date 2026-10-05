package com.mycompany.hotelsystem;

import java.util.Scanner;

public class HotelSystem {

    public static void cariKamar(String noKamar, Kamar[] daftar, int jumlah) {
        System.out.println("\n[Pencarian - String] Mencari kamar dengan Nomor: " + noKamar);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNomorKamar().equalsIgnoreCase(noKamar)) {
                System.out.print("Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kamar tidak ditemukan.");
        }
    }

    public static void cariKamar(double hargaMaksimal, Kamar[] daftar, int jumlah) {
        System.out.println("\n[Pencarian - Double] Mencari kamar dengan Harga <= Rp " + hargaMaksimal);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getHargaPerMalam() <= hargaMaksimal) {
                System.out.print("Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada kamar yang sesuai batasan harga tersebut.");
        }
    }

    public static void simulasiLayanan(Kamar item) {
        System.out.print("Simulasi Layanan Kamar " + item.getNomorKamar() + " ");
        item.layananKamar();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Kamar[] daftarKamar = new Kamar[10];
        int jumlahKamar = 0;
        boolean isRunning = true;

        daftarKamar[jumlahKamar++] = new KamarReguler("101", "Standard", 350000, true);
        daftarKamar[jumlahKamar++] = new KamarReguler("102", "Superior", 450000, false);
        daftarKamar[jumlahKamar++] = new KamarSuite("501", "Executive", 1200000, true);
        daftarKamar[jumlahKamar++] = new KamarSuite("502", "Presidential", 2500000, true);
        daftarKamar[jumlahKamar++] = new KamarPenthouse("PH1", "Royal", 5000000, true);

        System.out.println("=================================================");
        System.out.println("      SISTEM MANAJEMEN RESERVASI HOTEL           ");
        System.out.println("=================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Data Kamar Baru");
            System.out.println("2. Tampilkan Seluruh Kamar (Runtime Polymorphism & Dynamic Binding)");
            System.out.println("3. Cari Kamar (Compile-Time Polymorphism / Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahKamar < daftarKamar.length) {
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
                            daftarKamar[jumlahKamar++] = new KamarReguler(nomor, tipe, harga, sarapan);
                            System.out.println("[Sukses] Kamar Reguler berhasil ditambahkan.");
                        } else if (jenis == 2) {
                            System.out.print("Akses Lounge VIP? (true/false): ");
                            boolean lounge = scanner.nextBoolean();
                            scanner.nextLine();
                            daftarKamar[jumlahKamar++] = new KamarSuite(nomor, tipe, harga, lounge);
                            System.out.println("[Sukses] Kamar Suite berhasil ditambahkan.");
                        } else if (jenis == 3) {
                            System.out.print("Akses Helipad Pribadi? (true/false): ");
                            boolean helipad = scanner.nextBoolean();
                            scanner.nextLine();
                            daftarKamar[jumlahKamar++] = new KamarPenthouse(nomor, tipe, harga, helipad);
                            System.out.println("[Sukses] Kamar Penthouse berhasil ditambahkan.");
                        } else {
                            System.out.println("[Error] Jenis pilihan kamar tidak valid.");
                        }
                    } else {
                        System.out.println("[Error] Kapasitas sistem kamar penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- DAFTAR SELURUH KAMAR HOTEL (DYNAMIC BINDING) ---");
                    if (jumlahKamar == 0) {
                        System.out.println("Belum ada data kamar yang tersimpan.");
                    } else {
                        for (int i = 0; i < jumlahKamar; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarKamar[i].tampilkanInfo();

                            simulasiLayanan(daftarKamar[i]);
                            System.out.println();
                        }
                    }
                    System.out.println("Total Objek Tercipta (Static Counter): " + Kamar.getTotalKamarDibuat());
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n--- FITUR PENCARIAN (COMPILE-TIME POLYMORPHISM) ---");
                    System.out.println("1. Cari Berdasarkan Nomor Kamar (String)");
                    System.out.println("2. Cari Berdasarkan Harga Maksimal (double)");
                    System.out.print("Pilih Mode (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Nomor Kamar: ");
                        String kataKunci = scanner.nextLine();
                        cariKamar(kataKunci, daftarKamar, jumlahKamar);
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Harga Maksimal (Rp): ");
                        double hargaKunci = scanner.nextDouble();
                        scanner.nextLine();
                        cariKamar(hargaKunci, daftarKamar, jumlahKamar);
                    } else {
                        System.out.println("[Error] Mode pencarian tidak valid.");
                    }

                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("\nTerima kasih telah menggunakan Sistem Reservasi Hotel!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("[Error] Pilihan menu tidak valid. Masukkan angka 1-4.");
            }
        }

        scanner.close();
    }
}