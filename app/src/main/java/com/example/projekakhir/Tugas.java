package com.example.projekakhir;

public class Tugas {
    private String judul;
    private String desc;
    private String deadline;
    private String key;

    public Tugas(){}

    public Tugas(String judul, String desc, String deadline) {
        this.judul = judul;
        this.desc = desc;
        this.deadline = deadline;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
