package com.tiara.pengembalian_service.vo;

import java.time.LocalDate;

public class Peminjaman {
    private Long id;
    private Long anggotaId;
    private Long bukuId;
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalPengembalian;
    private Long pengembalianId;
    public Peminjaman(Long id, Long anggotaId, Long bukuId, LocalDate tanggalPeminjaman, LocalDate tanggalPengembalian,
            Long pengembalianId) {
        this.id = id;
        this.anggotaId = anggotaId;
        this.bukuId = bukuId;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
        this.pengembalianId = pengembalianId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAnggotaId() {
        return anggotaId;
    }
    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }
    public Long getBukuId() {
        return bukuId;
    }
    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }
    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }
    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }
    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }
    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }
    public Long getPengembalianId() {
        return pengembalianId;
    }
    public void setPengembalianId(Long pengembalianId) {
        this.pengembalianId = pengembalianId;
    }
    
    

    

}
