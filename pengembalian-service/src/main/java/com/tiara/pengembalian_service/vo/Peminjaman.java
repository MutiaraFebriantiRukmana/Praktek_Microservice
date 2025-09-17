package com.tiara.pengembalian_service.vo;

public class Peminjaman {
    private Long id;
    private Long anggotaId;
    private Long bukuId;
    private String tanggal_pinjam;
    private String tanggal_kembali;
    private Long pengembalianId;
    public Peminjaman(Long id, Long anggotaId, Long bukuId, String tanggal_pinjam, String tanggal_kembali,
            Long pengembalianId) {
        this.id = id;
        this.anggotaId = anggotaId;
        this.bukuId = bukuId;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
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
    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }
    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }
    public String getTanggal_kembali() {
        return tanggal_kembali;
    }
    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }
    public Long getPengembalianId() {
        return pengembalianId;
    }
    public void setPengembalianId(Long pengembalianId) {
        this.pengembalianId = pengembalianId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    

}
