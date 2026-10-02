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

    public void tampilkanInfo() {
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Nominal      : Rp" + nominal);
    }
}
