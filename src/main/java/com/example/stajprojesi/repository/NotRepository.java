package com.example.stajprojesi.repository;

import com.example.stajprojesi.model.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotRepository extends JpaRepository<Not, Long> {
    //  yalnızca belirli bir öğrencinin notlarını getirme
    List<Not> findByOgrenciId(Long ogrenciId);
}
