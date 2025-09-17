package com.tiara.peminjaman_service.service;
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

@Service
public class PeminjamanService {
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Peminjaman createPeminjaman(Peminjaman peminjaman) {
        return peminjamanRepository.save(peminjaman);
    }

    public List<Peminjaman> getAllPeminjamans() {
        return peminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id).orElse(null);
    }
    
    public ResponseTemplate getPeminjamanWithDetailsById(Long id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElse(null);
        if (peminjaman == null) {
            return null; 
        }
    
      
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("API-GATEWAY1");
        
        Anggota anggota = restTemplate.getForObject(
                serviceInstances.get(0).getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class);
    
        Buku buku = restTemplate.getForObject(
                serviceInstances.get(0).getUri() + "/api/buku/" + peminjaman.getBukuId(),
                Buku.class);

    
        ResponseTemplate response = new ResponseTemplate();
        response.setPeminjaman(peminjaman);
        response.setAnggota(anggota);
        response.setBuku(buku);
    
        return response;
    }

    public void deletePeminjaman(Long id) {
        peminjamanRepository.deleteById(id);
    }
    
    
    public Pengembalian createPengembalian(Pengembalian pengembalian) {
        ServiceInstance pengembalianInstance = discoveryClient.getInstances("PENGEMBALIAN").get(0);
        return restTemplate.postForObject(pengembalianInstance.getUri() + "/api/pengembalian", 
                                          pengembalian, Pengembalian.class);
    }

    
    public Pengembalian getPengembalianById(Long id) {
        ServiceInstance pengembalianInstance = discoveryClient.getInstances("PENGEMBALIAN").get(0);
        return restTemplate.getForObject(pengembalianInstance.getUri() + "/api/pengembalian/" + id, 
                                          Pengembalian.class);
    }
}