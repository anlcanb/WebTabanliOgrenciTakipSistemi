package com.example.stajprojesi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;              //  Lombok importları

@Entity
@Table(name = "notlar")
@Data                      //  Tüm getter, setter, toString, equals, hashCode
@NoArgsConstructor         //  Boş ctor
@AllArgsConstructor        //  All-args ctor
public class Not {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ogrenci_id", nullable = false)
    private Long ogrenciId;

    @NotNull
    @Column(name = "ders_id", nullable = false)
    private Long dersId;

    @NotNull
    @Min(value = 0, message = "Not en az 0 olmalı")
    @Max(value = 100, message = "Not en fazla 100 olmalı")
    @Column(name = "not_degeri", nullable = false)
    private Double notDegeri;
}
