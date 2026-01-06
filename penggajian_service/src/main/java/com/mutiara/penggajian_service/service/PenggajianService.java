package com.mutiara.penggajian_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutiara.penggajian_service.model.penggajian;
import com.mutiara.penggajian_service.repository.PenggajianRepository;

@Service
public class PenggajianService {

    @Autowired
    private PenggajianRepository penggajianRepository;

   
    public List<penggajian> getAllPenggajian() {
        return penggajianRepository.findAll();
    }

    public Optional<penggajian> getPenggajianById(Long id) {
        return penggajianRepository.findById(id);
    }

    public penggajian savePenggajian(penggajian p) {
        double total = p.getGajiPokok() + p.getTunjangan();
        double gajiBersih = total - p.getPotongan();

        p.setTotal(total);
        p.setGajiBersih(gajiBersih);

        return penggajianRepository.save(p);
    }

    public void deletePenggajian(Long id) {
        penggajianRepository.deleteById(id);
    }
    
    public penggajian updatePenggajian(Long id, penggajian details) {
        return penggajianRepository.findById(id).map(p -> {
            p.setNip(details.getNip());
            p.setNama(details.getNama());
            p.setGajiPokok(details.getGajiPokok());
            p.setGolongan(details.getGolongan());
            p.setTunjangan(details.getTunjangan());
            p.setPotongan(details.getPotongan());
            
            double total = details.getGajiPokok() + details.getTunjangan();
            double gajiBersih = total - details.getPotongan();
            
            p.setTotal(total);
            p.setGajiBersih(gajiBersih);
            
            return penggajianRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Data tidak ditemukan dengan id " + id));
    }
}