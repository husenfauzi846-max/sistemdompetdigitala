package com.mycompany.sistemdompetdigitalapp;

public class Transfer extends DompetDigital {
    private String tujuan;

    public Transfer(String idTransaksi, double nominal, String tujuan) {
        super(idTransaksi, nominal); 
        this.tujuan = tujuan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[JENIS TRANSAKSI : TRANSFER DANA]");
        System.out.println("ID Transaksi     : " + getIdTransaksi()); 
        System.out.println("Penerima Tujuan  : " + tujuan);
        System.out.println("Jumlah Transfer  : Rp" + getNominal());
    }
}