package com.mycompany.sistemdompetdigitalapp;

public class PembayaranTagihan extends DompetDigital implements DapatDicicil, MendapatkanPoin {
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

    @Override
    public void prosesTransaksi() {
        System.out.println("Pembayaran tagihan " + jenisTagihan + " berhasil diproses.");
    }

    @Override
    public void hitungCicilan(int jumlahBulan) {
        double cicilanPerBulan = getNominal() / jumlahBulan;
        System.out.printf("Simulasi Cicilan (%d bulan): Rp %,.2f per bulan\n", jumlahBulan, cicilanPerBulan);
    }

    @Override
    public double getBungaCicilan() {
        return 0.025; // Bunga cicilan sebesar 2.5%
    }

    @Override
    public int hitungPoinReward() {
        return (int) (getNominal() / 20000); // Poin reward dari pembayaran tagihan
    }
}