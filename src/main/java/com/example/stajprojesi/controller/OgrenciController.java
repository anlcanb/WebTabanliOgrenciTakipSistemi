package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Ogrenci;
import com.example.stajprojesi.repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogrenciler")
public class OgrenciController {

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @GetMapping
    public List<Ogrenci> getAll() {
        return ogrenciRepository.findAll();
    }

    @PostMapping
    public Ogrenci create(@RequestBody Ogrenci ogrenci) {
        return ogrenciRepository.save(ogrenci);
    }

    @PutMapping("/{id}")
    public Ogrenci update(@PathVariable Long id, @RequestBody Ogrenci ogrenci) {
        ogrenci.setId(id);
        return ogrenciRepository.save(ogrenci);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ogrenciRepository.deleteById(id);
    }
}
