package com.mycompany.sistemdompetdigitalapp;

public class User {
    private String nama;
    private String nomorTelepon;
    private double saldo;
    private static int totalUser = 0;

    public User(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.saldo = 0.0;
        totalUser++; 
    }

    public String getNama() {
        return nama;
    }
    
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void tambahSaldo(double jumlah) { 
        this.saldo += jumlah; 
    }
    
    public void kurangiSaldo(double jumlah) { 
        this.saldo -= jumlah;
    }

    public static int getTotalUser() {
        return totalUser;
    }
}