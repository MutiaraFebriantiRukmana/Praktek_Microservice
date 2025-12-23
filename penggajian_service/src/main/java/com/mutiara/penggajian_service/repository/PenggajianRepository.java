package com.mutiara.penggajian_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutiara.penggajian_service.model.penggajian;

public interface PenggajianRepository extends JpaRepository<penggajian, Long> {
    
}