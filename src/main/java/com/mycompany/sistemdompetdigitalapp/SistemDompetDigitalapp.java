package com.mycompany.sistemdompetdigitalapp;

import java.util.Scanner;

public class SistemDompetDigitalapp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DompetDigital[] daftarRiwayat = new DompetDigital[100];
        int jumlahTransaksi = 0;

        System.out.println("=============================================");
        System.out.println("    APLIKASI Dompet Digital E-WALLET         ");
        System.out.println("=============================================");
        
        System.out.print("Masukkan Nama Anda     : ");
        String namaInput = scanner.nextLine();
        
        System.out.print("Masukkan Nomor Telepon : ");
        String noTelpInput = scanner.nextLine();

        User akunUser = new User(namaInput, noTelpInput);
        System.out.println("\nRegistrasi Berhasil! Halo, " + akunUser.getNama() + "\n");
        
        boolean isRunning = true;
        
        
        while (isRunning) {
            System.out.println("========================================");
            System.out.println("               MENU UTAMA               ");
            System.out.println("========================================");
            System.out.println("1. Cek Profil & Saldo");
            System.out.println("2. Top-Up Saldo");
            System.out.println("3. Transfer ke Pengguna Lain");
            System.out.println("4. Bayar Tagihan");
            System.out.println("5. Lihat Riwayat Transaksi");
            System.out.println("6. Cetak Struk Transaksi");
            System.out.println("7. Pencarian Transaksi");
            System.out.println("8. Keluar Program");
            System.out.print("Silakan pilih menu (1-8): ");
            
            
            if (!scanner.hasNextInt()) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 
            

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- INFORMASI PROFIL & SALDO ---");
                    System.out.println("Nama Lengkap  : " + akunUser.getNama());
                    System.out.println("Nomor Telepon : " + akunUser.getNomorTelepon());
                    System.out.printf("Saldo Dompet  : Rp %,.2f\n", akunUser.getSaldo());
                    break;

                case 2:
                    if (jumlahTransaksi < daftarRiwayat.length) {
                        System.out.print("\nMasukkan Nominal Top-Up: Rp ");
                        double jmlTopUp = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Pilih Metode Bank (Bank / E-Wallet): ");
                        String metode = scanner.nextLine();

                        akunUser.tambahSaldo(jmlTopUp);
                        TopUp transaksiTopUp = new TopUp("TRX-UP-" + (jumlahTransaksi + 1), jmlTopUp, metode);
                        daftarRiwayat[jumlahTransaksi] = transaksiTopUp;
                        
                        // Fungsu untuk Memanggil method prosesTransaksi dari Abstract Class
                        transaksiTopUp.prosesTransaksi();
                        System.out.println("Poin Reward didapat: " + transaksiTopUp.hitungPoinReward() + " poin.");
                        
                        jumlahTransaksi++;
                        System.out.println("Top-Up berhasil! Saldo Anda bertambah.");
                    } else {
                        System.out.println("Kapasitas riwayat penuh!");
                    }
                    break;

                case 3:
                    if (jumlahTransaksi < daftarRiwayat.length) {
                        System.out.print("\nMasukkan Nominal Transfer: Rp ");
                        double jmlTransfer = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Masukkan No HP / Nama Tujuan: ");
                        String tujuan = scanner.nextLine();

                        if (akunUser.getSaldo() >= jmlTransfer) {
                            akunUser.kurangiSaldo(jmlTransfer);                       
                            Transfer transaksiTransfer = new Transfer("TRX-TF-" + (jumlahTransaksi + 1), jmlTransfer, tujuan);
                            daftarRiwayat[jumlahTransaksi] = transaksiTransfer;
                            
                            transaksiTransfer.prosesTransaksi();
                            jumlahTransaksi++;
                            System.out.println("Transfer berhasil dikirim ke " + tujuan);
                        } else {
                            System.out.println("Transaksi Gagal! Saldo tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Kapasitas riwayat penuh!");
                    }
                    break;

                case 4:
                    if (jumlahTransaksi < daftarRiwayat.length) {
                        System.out.print("\nMasukkan Nominal Tagihan: Rp ");
                        double jmlTagihan = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Masukkan Jenis Tagihan (Listrik/PDAM/Internet): ");
                        String jenis = scanner.nextLine();

                        if (akunUser.getSaldo() >= jmlTagihan) {
                            akunUser.kurangiSaldo(jmlTagihan);
                            PembayaranTagihan transaksiTagihan = new PembayaranTagihan("TRX-PG-" + (jumlahTransaksi + 1), jmlTagihan, jenis);
                            daftarRiwayat[jumlahTransaksi] = transaksiTagihan;
                            
                            transaksiTagihan.prosesTransaksi();
                            
                            // fungsi penerapan method Interface DapatDicicil
                            System.out.print("Apakah ingin disimulasikan cicilan? (y/n): ");
                            String opsiCicil = scanner.nextLine();
                            if (opsiCicil.equalsIgnoreCase("y")) {
                                System.out.print("Masukkan jumlah bulan cicilan (contoh: 3 / 6 / 12): ");
                                int bulan = scanner.nextInt();
                                scanner.nextLine();
                                transaksiTagihan.hitungCicilan(bulan);
                                System.out.println("Bunga Cicilan: " + (transaksiTagihan.getBungaCicilan() * 100) + "%");
                            }

                            jumlahTransaksi++;
                            System.out.println("Pembayaran tagihan " + jenis + " berhasil!");
                        } else {
                            System.out.println("Transaksi Gagal! Saldo tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Kapasitas riwayat penuh!");
                    }
                    break;

                case 5:
                    System.out.println("\n--- DAFTAR RIWAYAT TRANSAKSI ---");
                    if (jumlahTransaksi == 0) {
                        System.out.println("Belum ada riwayat transaksi yang tercatat.");
                    } else {
                        for (int i = 0; i < jumlahTransaksi; i++) {
                            System.out.println("Transaksi ke-" + (i + 1) + ":");
                            daftarRiwayat[i].tampilkanInfo(); 
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n--- CETAK STRUK TRANSAKSI ---");
                    if (jumlahTransaksi == 0) {
                        System.out.println("Belum ada transaksi.");
                    } else {
                        System.out.print("Pilih nomor urut transaksi (1 s.d " + jumlahTransaksi + "): ");
                        int idx = scanner.nextInt();
                        scanner.nextLine();
                        if (idx >= 1 && idx <= jumlahTransaksi) {  
                            DompetDigital.cetakStrukTransaksi(daftarRiwayat[idx - 1]);
                        } else {
                            System.out.println("Nomor transaksi tidak valid.");
                        }
                    }
                    break;

                case 7:
                    System.out.println("\n--- PENCARIAN TRANSAKSI ---");
                    if (jumlahTransaksi == 0) {
                        System.out.println("Belum ada data transaksi.");
                    } else {
                        System.out.println("1. Cari Berdasarkan ID Transaksi");
                        System.out.println("2. Cari Berdasarkan Nomor Indeks");
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

                case 8:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi E-Wallet!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka 1 sampai 8.");
            }
            System.out.println();
        }
        scanner.close();
    }
}