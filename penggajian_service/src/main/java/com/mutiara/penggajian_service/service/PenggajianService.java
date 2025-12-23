package com.mutiara.penggajian_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutiara.penggajian_service.model.penggajian;
import com.mutiara.penggajian_service.repository.PenggajianRepository;

@Service
public class PenggajianService {
     @Autowired
    private PenggajianRepository penggajianRepository;

    public List<penggajian> getAllpenggajians(){
    return penggajianRepository.findAll();
    }

    public  Penggajian get PenggajianById(Long id) {
    return penggajianRepository.findById(id).orElse(null);
    }

    public Penggajian createPenggajian(Penggajian penggajian){
    return penggajianRepository.save(penggajian);
    }

    public void deletePenggajian (Long id){
    penggajianRepository.deleteById(id);
    }

    

}
