package com.mycompany.sistemdompetdigitalapp;

public class Transfer extends DompetDigital {
    private String tujuan;

    public Transfer(String idTransaksi, double nominal, String tujuan) {
        super(idTransaksi, nominal); 
        this.tujuan = tujuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[JENIS TRANSAKSI : TRANSFER DANA]");
        System.out.println("ID Transaksi     : " + getIdTransaksi()); 
        System.out.println("Penerima Tujuan  : " + tujuan);
        System.out.printf("Jumlah Transfer  : Rp %,.2f\n", getNominal());
    }

    @Override
    public void prosesTransaksi() {
        System.out.println("Transfer dana ke tujuan " + tujuan + " berhasil diproses.");
    }
}