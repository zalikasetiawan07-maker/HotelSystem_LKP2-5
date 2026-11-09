package com.mycompany.hotelsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hotel hotelUnila = new Hotel("Grand Hotel Jakarta");
        hotelUnila.bukaHotel();

        try {
            hotelUnila.tambahKamar(new KamarReguler("101", "Standard", 350000, true));
            hotelUnila.tambahKamar(new KamarReguler("102", "Superior", 450000, false));
            hotelUnila.tambahKamar(new KamarSuite("501", "Executive", 1200000, true));
            hotelUnila.tambahKamar(new KamarSuite("502", "Presidential", 2500000, true));
            hotelUnila.tambahKamar(new KamarPenthouse("PH1", "Royal", 5000000, true));
        } catch (Exception e) {
            System.out.println("Error Inisialisasi Data Kamar: " + e.getMessage());
        }

        hotelUnila.tambahTamu(new Tamu("187101001", "Yeni"));
        hotelUnila.tambahTamu(new Tamu("187101002", "Budi Santoso"));
        hotelUnila.tambahTamu(new Tamu("187101003", "Agus"));

        boolean isRunning = true;

        System.out.println("\n==========================================================");
        System.out.println("   SISTEM MANAJEMEN HOTEL (MODUL 10 RELASI ANTAR-OBJECT)  ");
        System.out.println("==========================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kamar Baru");
            System.out.println("2. Lihat Seluruh Kamar");
            System.out.println("3. Cari Kamar (Custom Exception)");
            System.out.println("4. Manajemen Data Tamu");
            System.out.println("5. Peminjaman / Reservasi (Demo Association)");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu (1-6): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
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
                            hotelUnila.tambahKamar(new KamarReguler(nomor, tipe, harga, sarapan));
                        } else if (jenis == 2) {
                            System.out.print("Akses Lounge VIP? (true/false): ");
                            boolean lounge = scanner.nextBoolean();
                            scanner.nextLine();
                            hotelUnila.tambahKamar(new KamarSuite(nomor, tipe, harga, lounge));
                        } else if (jenis == 3) {
                            System.out.print("Akses Helipad Pribadi? (true/false): ");
                            boolean helipad = scanner.nextBoolean();
                            scanner.nextLine();
                            hotelUnila.tambahKamar(new KamarPenthouse(nomor, tipe, harga, helipad));
                        } else {
                            System.out.println("[Error] Pilihan jenis kamar tidak valid.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- DAFTAR SELURUH KAMAR HOTEL ---");
                        hotelUnila.lihatSemuaKamar();
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\n--- FITUR CARI KAMAR ---");
                        System.out.print("Masukkan Nomor Kamar yang dicari: ");
                        String kataKunci = scanner.nextLine();
                        try {
                            hotelUnila.cariKamar(kataKunci);
                        } catch (KamarTidakDitemukanException e) {
                            System.out.println("[PENANGANAN ERROR] " + e.getMessage());
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 4:
                        System.out.println("\n--- KELOLA DATA TAMU ---");
                        System.out.println("1. Tambah Tamu Baru");
                        System.out.println("2. Cari Tamu via NIK");
                        System.out.println("3. Lihat Semua Tamu");
                        System.out.print("Pilih Opsi (1-3): ");
                        int menuTamu = scanner.nextInt();
                        scanner.nextLine();

                        if (menuTamu == 1) {
                            System.out.print("Masukkan NIK Tamu : ");
                            String nik = scanner.nextLine();
                            System.out.print("Masukkan Nama     : ");
                            String nama = scanner.nextLine();
                            hotelUnila.tambahTamu(new Tamu(nik, nama));
                        } else if (menuTamu == 2) {
                            System.out.print("Masukkan NIK yang dicari: ");
                            hotelUnila.cariTamu(scanner.nextLine());
                        } else if (menuTamu == 3) {
                            hotelUnila.lihatSemuaTamu();
                        } else {
                            System.out.println("[Error] Opsi menu tidak valid.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 5:
                        System.out.println("\n--- SISTEM RESERVASI (DEMO ASOSIASI) ---");
                        System.out.print("Masukkan NIK Tamu Peminjam: ");
                        String inputNik = scanner.nextLine();
                        Tamu peminjam = hotelUnila.getTamu(inputNik);

                        if (peminjam == null) {
                            System.out.println("[Gagal] Tamu dengan NIK tersebut belum terdaftar!");
                        } else if (hotelUnila.getJumlahKamar() == 0) {
                            System.out.println("[Gagal] Belum ada kamar yang tersedia.");
                        } else {
                            hotelUnila.lihatSemuaKamar();
                            System.out.print("Pilih Nomor Urut Kamar (1-" + hotelUnila.getJumlahKamar() + "): ");
                            int noUrut = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (noUrut >= 0 && noUrut < hotelUnila.getJumlahKamar()) {
                                Kamar itemKamar = hotelUnila.getKamarIndex(noUrut);
                                TransaksiReservasi.prosesReservasi(peminjam, itemKamar);
                            } else {
                                System.out.println("[Error] Nomor urut kamar tidak valid!");
                            }
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 6:
                        System.out.println("Terima kasih telah menggunakan Sistem Manajemen Hotel!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("[Error] Pilihan menu tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR INPUT] Harus memasukkan angka!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("[ERROR SISTEM] " + e.getMessage());
            }
        }

        scanner.close();
    }
}