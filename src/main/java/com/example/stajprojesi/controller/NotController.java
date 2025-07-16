package com.example.stajprojesi.controller;

import com.example.stajprojesi.model.Not;
import com.example.stajprojesi.repository.DersRepository;
import com.example.stajprojesi.repository.NotRepository;
import com.example.stajprojesi.repository.OgrenciRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class NotController {

    @Autowired
    private NotRepository notRepository;

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Autowired
    private DersRepository dersRepository;

    /** Tüm notları listele */
    @GetMapping("/notlar")
    public List<Not> getAll() {
        return notRepository.findAll();
    }


    @GetMapping("/ogrenciler/{ogrenciId}/notlar")
    public List<Not> getByOgrenci(@PathVariable Long ogrenciId) {
        return notRepository.findByOgrenciId(ogrenciId);
    }


    @GetMapping("/ogrenciler/{ogrenciId}/ortalama")
    public Map<String, Object> getOrtalama(@PathVariable Long ogrenciId) {
        Double ort = notRepository.findOrtalamaByOgrenciId(ogrenciId);
        String ortStr = ort != null ? String.format("%.2f", ort) : "0.00";
        String durum = ort != null && ort >= 60.0 ? "Başarılı " : "Başarısız ";

        Map<String,Object> resp = new HashMap<>();
        resp.put("ogrenciId", ogrenciId);
        resp.put("ortalama", ortStr);
        resp.put("durum", durum);
        return resp;
    }


    @GetMapping("/dersler/{dersId}/ortalama")
    public Map<String, Object> getDersOrt(@PathVariable Long dersId) {
        Double ort = notRepository.findOrtalamaByDersId(dersId);
        Map<String,Object> resp = new HashMap<>();
        resp.put("dersId", dersId);
        resp.put("ortalama", ort != null ? String.format("%.2f", ort) : "0.00");
        return resp;
    }


    @PostMapping("/notlar")
    public ResponseEntity<?> create(@Valid @RequestBody Not n) {
        boolean ogrenciVar = ogrenciRepository.existsById(n.getOgrenciId());
        boolean dersVar = dersRepository.existsById(n.getDersId());

        if (!ogrenciVar || !dersVar) {
            Map<String, String> hata = new HashMap<>();
            if (!ogrenciVar) hata.put("ogrenci", "Belirtilen öğrenci bulunamadı");
            if (!dersVar)    hata.put("ders", "Belirtilen ders bulunamadı");
            return ResponseEntity.badRequest().body(hata);
        }

        return ResponseEntity.ok(notRepository.save(n));
    }

    /** ID ile not güncelle */
    @PutMapping("/notlar/{id}")
    public Not update(@PathVariable Long id, @Valid @RequestBody Not n) {
        n.setId(id);
        return notRepository.save(n);
    }

    /** Öğrenci ve Ders ID'si üzerinden not güncelle */
    @Transactional
    @PutMapping("/ogrenciler/{ogrenciId}/dersler/{dersId}/not")
    public ResponseEntity<Void> updateByOgrenciDers(
            @PathVariable Long ogrenciId,
            @PathVariable Long dersId,
            @RequestBody Map<String, Double> body
    ) {
        Double yeniPuan = body.get("notDegeri");
        int affected = notRepository.updateNotByOgrenciAndDers(ogrenciId, dersId, yeniPuan);
        if (affected > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**  Not sil */
    @DeleteMapping("/notlar/{id}")
    public void delete(@PathVariable Long id) {
        notRepository.deleteById(id);
    }
}
