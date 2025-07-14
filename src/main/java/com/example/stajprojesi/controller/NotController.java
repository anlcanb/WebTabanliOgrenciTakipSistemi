package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Not;
import com.example.stajprojesi.repository.NotRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notlar")
public class NotController {

    @Autowired
    private NotRepository notRepository;

    @GetMapping
    public List<Not> getAll() {
        return notRepository.findAll();
    }

    @PostMapping
    public Not create(@Valid @RequestBody Not n) {
        return notRepository.save(n);
    }

    @PutMapping("/{id}")
    public Not update(@PathVariable Long id,
                      @Valid @RequestBody Not n) {
        n.setId(id);
        return notRepository.save(n);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notRepository.deleteById(id);
    }
}
