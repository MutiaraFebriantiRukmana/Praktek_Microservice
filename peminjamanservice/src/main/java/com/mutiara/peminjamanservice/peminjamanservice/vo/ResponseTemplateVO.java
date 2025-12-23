package com.mutiara.peminjamanservice.peminjamanservice.vo;

import com.mutiara.peminjamanservice.peminjamanservice.model.Peminjaman;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;
}