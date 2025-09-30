package com.tiara.peminjaman_service.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tiara.peminjaman_service.model.Peminjaman;
import com.tiara.peminjaman_service.repository.PeminjamanRepository;
import com.tiara.peminjaman_service.vo.Anggota;
import com.tiara.peminjaman_service.vo.Buku;
import com.tiara.peminjaman_service.vo.Pengembalian;
import com.tiara.peminjaman_service.vo.ResponseTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class PeminjamanService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

    
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Peminjaman createPeminjaman(Peminjaman peminjaman) {
        peminjaman.setTanggalPeminjaman(LocalDate.now());
        peminjaman.setTanggalPengembalian(LocalDate.now().plusDays(7));

        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);

        ServiceInstance anggotaServiceInstance = discoveryClient.getInstances("anggota-service").get(0);
        Anggota anggota = restTemplate.getForObject(anggotaServiceInstance.getUri() + "/api/anggota/"
                + savedPeminjaman.getAnggotaId(), Anggota.class);

        ServiceInstance bukuServiceInstance = discoveryClient.getInstances("buku-service").get(0);
        Buku buku = restTemplate.getForObject(bukuServiceInstance.getUri() + "/api/buku/"
                + savedPeminjaman.getBukuId(), Buku.class);


        if (anggota != null && anggota.getEmail() != null) {
            String subject = "Peminjaman Buku Berhasil!";
            
            String formattedTanggalPeminjaman = savedPeminjaman.getTanggalPeminjaman().format(DATE_FORMATTER);
            String formattedTanggalPengembalian = savedPeminjaman.getTanggalPengembalian().format(DATE_FORMATTER);

            String body = String.format(
                "Yth. Bapak/Ibu %s,\n\n" +
                "Anda telah berhasil melakukan peminjaman buku dengan rincian sebagai berikut:\n\n" +
                "--- Rincian Peminjaman ---\n" +
                "ID Peminjaman: %d\n" +
                "Judul Buku: %s\n" +
                "Tanggal Pinjam: %s\n" +
                "Tanggal Kembali: %s\n" +
                "\n\n" +
                "Mohon untuk mengembalikan buku tepat waktu untuk menghindari denda. Terima kasih.\n\n" +
                "Salam,\n" +
                "Admin Perpustakaan",
                anggota.getNama(),
                savedPeminjaman.getId(),
                buku != null ? buku.getJudul() : "Judul Tidak Diketahui",
                formattedTanggalPeminjaman,
                formattedTanggalPengembalian
            );

            emailService.sendEmail(anggota.getEmail(), subject, body);
        }

        try {
            String peminjamanJson = objectMapper.writeValueAsString(savedPeminjaman);
            rabbitMQProducer.sendMessage("Peminjaman Created: " + peminjamanJson);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting Peminjaman to JSON for RabbitMQ: " + e.getMessage());
        }

        return savedPeminjaman;
    }

    public List<Peminjaman> getAllPeminjamans() {
        return peminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id).orElse(null);
    }
    
    public List<ResponseTemplate> getPeminjamanWithBookById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Peminjaman peminjaman = getPeminjamanById(id);
        
        
        if (peminjaman == null) {
            return null;
        }

        ServiceInstance bukuServiceInstance = discoveryClient.getInstances("buku-service").get(0);
        Buku buku = restTemplate.getForObject(bukuServiceInstance.getUri() + "/api/buku/"
                + peminjaman.getBukuId(), Buku.class);
               
        ServiceInstance anggotaServiceInstance = discoveryClient.getInstances("anggota-service").get(0);
        Anggota anggota = restTemplate.getForObject(anggotaServiceInstance.getUri() + "/api/anggota/"
                + peminjaman.getAnggotaId(), Anggota.class);
        
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        return responseList;
    }
    public void deletePeminjaman (Long id){
      peminjamanRepository.deleteById(id);
      peminjamanRepository.deleteById(id);
      rabbitMQProducer.sendMessage("Peminjaman Deleted: ID " + id);
    }
}