package com.mutiara.penggajian_service.controller;

import com.mutiara.penggajian_service.model.penggajian;
import com.mutiara.penggajian_service.service.PenggajianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penggajian")
public class PenggajianController {

    @Autowired
    private PenggajianService penggajianService;

    @GetMapping
    public List<penggajian> getAll() {
        return penggajianService.getAllPenggajian();
    }

    @GetMapping("/{id}")
    public ResponseEntity<penggajian> getById(@PathVariable Long id) {
        return penggajianService.getPenggajianById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public penggajian create(@RequestBody penggajian p) {
        return penggajianService.savePenggajian(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<penggajian> update(@PathVariable Long id, @RequestBody penggajian p) {
        try {
            return ResponseEntity.ok(penggajianService.updatePenggajian(id, p));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        penggajianService.deletePenggajian(id);
        return ResponseEntity.noContent().build();
    }
}