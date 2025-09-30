package com.tiara.peminjaman_service.vo;

public class Anggota {
    private Long id;
    private String nim;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String email;

    public Anggota(String alamat, String email, Long id, String jenis_kelamin, String nama, String nim) {
        this.alamat = alamat;
        this.email = email;
        this.id = id;
        this.jenis_kelamin = jenis_kelamin;
        this.nama = nama;
        this.nim = nim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}