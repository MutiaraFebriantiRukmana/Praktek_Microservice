package com.tiara.buku_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiara.buku_service.model.Buku;

public interface BukuRepository extends JpaRepository<Buku, Long> {


}