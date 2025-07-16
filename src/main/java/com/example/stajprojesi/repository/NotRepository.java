package com.example.stajprojesi.repository;

import com.example.stajprojesi.model.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NotRepository extends JpaRepository<Not, Long> {

    /**
     Belirli bir öğrencinin tüm notlarını getirir.
     */
    List<Not> findByOgrenciId(Long ogrenciId);

    /**
      Öğrenci ve ders bazında tekil not kaydını getirir.
     */
    Optional<Not> findByOgrenciIdAndDersId(Long ogrenciId, Long dersId);

    /**
     Öğrenci ortalamasını hesaplar.
     */
    @Query("SELECT AVG(n.notDegeri) FROM Not n WHERE n.ogrenciId = :ogrenciId")
    Double findOrtalamaByOgrenciId(@Param("ogrenciId") Long ogrenciId);

    /**
     * Yeni: Ders ortalamasını hesaplar.
     */
    @Query("SELECT AVG(n.notDegeri) FROM Not n WHERE n.dersId = :dersId")
    Double findOrtalamaByDersId(@Param("dersId") Long dersId);

    /**Öğrenci ve ders bazında notu güncelleyen SQL sorgusu.*/
    @Modifying
    @Transactional
    @Query("""
        UPDATE Not n
        SET    n.notDegeri = :puan
        WHERE  n.ogrenciId = :ogrenciId
          AND  n.dersId    = :dersId
        """)
    int updateNotByOgrenciAndDers(
            @Param("ogrenciId") Long ogrenciId,
            @Param("dersId")    Long dersId,
            @Param("puan")      Double puan
    );
}

