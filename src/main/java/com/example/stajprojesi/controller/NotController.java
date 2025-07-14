package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Not;
import com.example.stajprojesi.repository.NotRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotController {

    @Autowired
    private NotRepository notRepository;

    //  tüm notları listeleyen metod
    @GetMapping("/notlar")
    public List<Not> getAll() {
        return notRepository.findAll();
    }

    //  yalnızca ilgili öğrencinin notlarını döner
    @GetMapping("/ogrenciler/{ogrenciId}/notlar")
    public List<Not> getByOgrenci(@PathVariable Long ogrenciId) {
        return notRepository.findByOgrenciId(ogrenciId);
    }

    @PostMapping("/notlar")
    public Not create(@Valid @RequestBody Not n) {
        return notRepository.save(n);
    }

    @PutMapping("/notlar/{id}")
    public Not update(@PathVariable Long id, @Valid @RequestBody Not n) {
        n.setId(id);
        return notRepository.save(n);
    }

    @DeleteMapping("/notlar/{id}")
    public void delete(@PathVariable Long id) {
        notRepository.deleteById(id);
    }
}