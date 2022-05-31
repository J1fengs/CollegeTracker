package com.example.projekakhir;

public class Matkul {
    private String nama;
    private String jadwal;
    private String waktu;

    public Matkul(){}
    public Matkul(String nama, String jadwal, String waktu) {
        this.nama = nama;
        this.jadwal = jadwal;
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }


}
