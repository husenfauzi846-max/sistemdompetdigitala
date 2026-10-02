package com.mycompany.sistemdompetdigitalapp;

public class TopUp extends DompetDigital {
    private String metodeTopUp;

    public TopUp(String idTransaksi, double nominal, String metodeTopUp) {
        super(idTransaksi, nominal);
        this.metodeTopUp = metodeTopUp;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[JENIS TRANSAKSI : TOP-UP SALDO]");
        System.out.println("ID Transaksi  : " + getIdTransaksi()); 
        System.out.println("Metode Bank   : " + metodeTopUp);
        System.out.println("Jumlah Top-Up : Rp" + getNominal());
    }
}