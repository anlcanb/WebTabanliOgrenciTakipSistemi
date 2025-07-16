package com.example.stajprojesi.repository;

import com.example.stajprojesi.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByEmail(String email);
}
