package com.example.stajprojesi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;


}
