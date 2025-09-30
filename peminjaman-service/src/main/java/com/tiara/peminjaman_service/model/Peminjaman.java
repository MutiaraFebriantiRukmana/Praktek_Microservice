package com.tiara.peminjaman_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate; // Import for LocalDate


@Data
@Table(name = "peminjamans")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long anggotaId;
    //private Long pengembalianId;

    private Long bukuId;
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalPengembalian;
    
}