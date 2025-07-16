package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Kullanici;
import com.example.stajprojesi.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Kullanici giris) {
        Optional<Kullanici> kayitli = kullaniciRepository.findByEmail(giris.getEmail());

        if (kayitli.isPresent()) {
            boolean eslesiyor = passwordEncoder.matches(giris.getSifre(), kayitli.get().getSifre());

            if (eslesiyor) {
                return ResponseEntity.ok(kayitli.get());
            }
        }

        return ResponseEntity.status(401).body("Geçersiz email veya şifre");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Kullanici yeni) {
        if (kullaniciRepository.findByEmail(yeni.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Bu email zaten kullanılıyor.");
        }

        yeni.setSifre(passwordEncoder.encode(yeni.getSifre()));
        return ResponseEntity.ok(kullaniciRepository.save(yeni));
    }
}
