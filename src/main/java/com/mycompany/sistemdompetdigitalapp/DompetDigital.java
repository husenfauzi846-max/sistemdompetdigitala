package com.mycompany.sistemdompetdigitalapp;

public class DompetDigital {
    private String idTransaksi;
    private double nominal;

    public DompetDigital(String idTransaksi, double nominal) {
        this.idTransaksi = idTransaksi;
        this.nominal = nominal;
    }

    public String getIdTransaksi() {
        return idTransaksi; 
    }

    public double getNominal() {
        return nominal;
    }

    // Method dasar yang akan di-override oleh seluruh subclass
    public void tampilkanInfo() {
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.printf("Nominal      : Rp %,.2f\n", nominal);
    }

    // ==========================================================
    // 1. RUNTIME POLYMORPHISM & DYNAMIC BINDING (Parameter Superclass / Upcasting)
    // ==========================================================
    public static void prosesSimulasiTransaksi(DompetDigital objekTransaksi) {
        System.out.println("\n[SIMULASI PROSES TRANSAKSI (DYNAMIC BINDING)]");
        System.out.println("Memproses objek bertipe Superclass...");
        // JVM secara dinamis menentukan method tampilkanInfo() mana yang dijalankan 
        // berdasarkan wujud asli objek saat runtime (TopUp, Transfer, atau Tagihan)
        objekTransaksi.tampilkanInfo();
        System.out.println("Status: Transaksi berhasil diproses oleh sistem.\n");
    }

    // ==========================================================
    // 2. COMPILE-TIME POLYMORPHISM (Method Overloading)
    // ==========================================================
    public static void cariTransaksi(DompetDigital[] list, int total, String keywordId) {
        boolean ditemukan = false;
        for (int i = 0; i < total; i++) {
            if (list[i].getIdTransaksi().equalsIgnoreCase(keywordId)) {
                System.out.println("\n[DATA DITEMUKAN BERDASARKAN ID]");
                list[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Transaksi dengan ID '" + keywordId + "' tidak ditemukan.");
        }
    }

    public static void cariTransaksi(DompetDigital[] list, int total, int index) {
        if (index >= 0 && index < total) {
            System.out.println("\n[DATA DITEMUKAN BERDASARKAN INDEKS]");
            list[index].tampilkanInfo();
        } else {
            System.out.println("Indeks transaksi di luar batas yang tersedia!");
        }
    }
}