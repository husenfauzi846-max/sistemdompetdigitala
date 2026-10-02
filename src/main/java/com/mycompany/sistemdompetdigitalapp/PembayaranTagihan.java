package com.mycompany.sistemdompetdigitalapp;

public class PembayaranTagihan extends DompetDigital {
    private String jenisTagihan; 

    public PembayaranTagihan(String idTransaksi, double nominal, String jenisTagihan) {
        super(idTransaksi, nominal);
        this.jenisTagihan = jenisTagihan;
    }

    public String getJenisTagihan() {
        return jenisTagihan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[JENIS TRANSAKSI : PEMBAYARAN TAGIHAN]");
        System.out.println("ID Transaksi     : " + getIdTransaksi());
        System.out.println("Jenis Tagihan    : " + jenisTagihan);
        System.out.printf("Total Tagihan    : Rp %,.2f\n", getNominal());
    }
}