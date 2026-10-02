package com.mycompany.sistemdompetdigitalapp;

import java.util.Scanner;

public class SistemDompetDigitalapp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        DompetDigital[] daftarRiwayat = new DompetDigital[100];
        int jumlahTransaksi = 0;

        System.out.println("=============================================");
        System.out.println("    APLIKASI MINI Dompet Digital E-WALLET    ");
        System.out.println("=============================================");
        
        System.out.print("Masukkan Nama Anda     : ");
        String namaInput = scanner.nextLine();
        
        System.out.print("Masukkan Nomor Telepon : ");
        String noTelpInput = scanner.nextLine();

        User akunUser = new User(namaInput, noTelpInput);
        System.out.println("\nRegistrasi Berhasil! Halo, " + akunUser.getNama() + " (Total User: " + User.getTotalUser() + ")\n");
        boolean isRunning = true;
        
        while (isRunning) {
            System.out.println("========================================");
            System.out.println("                MENU UTAMA              ");
            System.out.println("========================================");
            System.out.println("1. Cek Profil & Saldo");
            System.out.println("2. Top-Up Saldo");
            System.out.println("3. Transfer ke Pengguna Lain");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Cari Transaksi Khusus (Method Overloading)");
            System.out.println("6. Keluar Program");
            System.out.print("Silakan pilih menu (1-6): ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- INFORMASI PROFIL & SALDO ---");
                    System.out.println("Nama Lengkap  : " + akunUser.getNama());
                    System.out.println("Nomor Telepon : " + akunUser.getNomorTelepon());
                    System.out.println("Saldo Dompet  : Rp" + akunUser.getSaldo());
                    break;

                case 2:
                    if (jumlahTransaksi < daftarRiwayat.length) {
                        System.out.print("\nMasukkan Nominal Top-Up: Rp");
                        double jmlTopUp = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Pilih Metode Bank (Bank / E-Wallet): ");
                        String metode = scanner.nextLine();

                        akunUser.tambahSaldo(jmlTopUp);
                        
                        daftarRiwayat[jumlahTransaksi] = new TopUp("TRX-UP-" + (jumlahTransaksi + 1), jmlTopUp, metode);
                        jumlahTransaksi++;
                        
                        System.out.println("Top-Up berhasil! Saldo Anda telah bertambah.");
                    } else {
                        System.out.println("Kapasitas riwayat transaksi penuh!");
                    }
                    break;

                case 3:
                    if (jumlahTransaksi < daftarRiwayat.length) {
                        System.out.print("\nMasukkan Nominal Transfer: Rp");
                        double jmlTransfer = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Masukkan No HP / Nama Tujuan: ");
                        String tujuan = scanner.nextLine();

                        if (akunUser.getSaldo() >= jmlTransfer) {
                            akunUser.kurangiSaldo(jmlTransfer);
                            
                            daftarRiwayat[jumlahTransaksi] = new Transfer("TRX-TF-" + (jumlahTransaksi + 1), jmlTransfer, tujuan);
                            jumlahTransaksi++;
                            
                            System.out.println("Transfer berhasil dikirim ke " + tujuan);
                        } else {
                            System.out.println("Transaksi Gagal! Saldo Anda tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Kapasitas riwayat transaksi penuh!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- DAFTAR RIWAYAT TRANSAKSI ---");
                    if (jumlahTransaksi == 0) {
                        System.out.println("Belum ada riwayat transaksi yang tercatat.");
                    } else {
                        for (int i = 0; i < jumlahTransaksi; i++) {
                            System.out.println("Transaksi ke-" + (i + 1) + ":");
                            daftarRiwayat[i].tampilkanInfo(); // Dynamic Binding
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;

                case 5:
                    // Demonstrasi Pemanggilan Method Overloading
                    System.out.println("\n--- PENCARIAN TRANSAKSI (OVERLOADING) ---");
                    if (jumlahTransaksi == 0) {
                        System.out.println("Belum ada data transaksi.");
                    } else {
                        System.out.println("1. Cari Berdasarkan ID Transaksi (String)");
                        System.out.println("2. Cari Berdasarkan Nomor Urut / Indeks (int)");
                        System.out.print("Pilih opsi (1/2): ");
                        int subPilih = scanner.nextInt();
                        scanner.nextLine();

                        if (subPilih == 1) {
                            System.out.print("Masukkan ID (cth: TRX-UP-1): ");
                            String idCari = scanner.nextLine();
                            DompetDigital.cariTransaksi(daftarRiwayat, jumlahTransaksi, idCari); 
                        } else if (subPilih == 2) {
                            System.out.print("Masukkan indeks (0 s.d " + (jumlahTransaksi - 1) + "): ");
                            int idxCari = scanner.nextInt();
                            scanner.nextLine();
                            DompetDigital.cariTransaksi(daftarRiwayat, jumlahTransaksi, idxCari); 
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;

                case 6:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi E-Wallet!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka 1 sampai 6.");
            }
            System.out.println();
        }
        scanner.close();
    }
}