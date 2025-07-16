
package com.example.stajprojesi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String sifre;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        OGRENCI,
        OGRETMEN
    }
}
