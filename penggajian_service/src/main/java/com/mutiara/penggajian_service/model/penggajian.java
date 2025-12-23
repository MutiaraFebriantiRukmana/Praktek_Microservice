package com.mutiara.penggajian_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "penggajians")
public class penggajian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noTransaksi;
    private Integer nip;
    private String nama;
    private Double gajiPokok;
    private String golongan;
    private Double tunjangan;
    private Double gajiBersih;
    private Double potongan;
    private Double total;
}

