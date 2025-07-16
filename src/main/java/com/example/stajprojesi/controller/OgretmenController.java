package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Ogretmen;
import com.example.stajprojesi.repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ogretmenler")
public class OgretmenController {

    @Autowired
    private OgretmenRepository ogretmenRepository;

    @GetMapping
    public List<Ogretmen> getAll() {
        return ogretmenRepository.findAll();
    }

    @PostMapping
    public Ogretmen create(@RequestBody Ogretmen ogretmen) {
        return ogretmenRepository.save(ogretmen);
    }

    @PutMapping("/{id}")
    public Ogretmen update(@PathVariable Long id, @RequestBody Ogretmen ogretmen) {
        ogretmen.setId(id);
        return ogretmenRepository.save(ogretmen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ogretmenRepository.deleteById(id);
    }
}
