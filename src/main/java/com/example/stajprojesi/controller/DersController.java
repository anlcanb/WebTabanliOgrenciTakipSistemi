package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Ders;
import com.example.stajprojesi.repository.DersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dersler")
public class DersController {

    @Autowired
    private DersRepository dersRepository;

    // Tüm dersleri listele
    @GetMapping
    public List<Ders> getAll() {
        return dersRepository.findAll();
    }

    // Yeni ders ekle
    @PostMapping
    public Ders create(@RequestBody Ders ders) {
        return dersRepository.save(ders);
    }

    // Mevcut dersi güncelle
    @PutMapping("/{id}")
    public Ders update(@PathVariable Long id, @RequestBody Ders ders) {
        ders.setId(id);
        return dersRepository.save(ders);
    }

    // Dersi sil
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dersRepository.deleteById(id);
    }
}
