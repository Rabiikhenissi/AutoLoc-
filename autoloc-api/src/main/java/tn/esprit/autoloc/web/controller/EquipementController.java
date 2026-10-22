package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipement;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipement equipementService;

    @GetMapping
    public List<Equipement> getAll() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getById(@PathVariable Long id) {
        Equipement equipement = equipementService.getEquipementById(id);
        if (equipement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipement);
    }

    @PostMapping
    public ResponseEntity<Equipement> addEquipement(@RequestBody Equipement equipement) {
        if (equipement == null || equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Equipement saved = equipementService.addEquipement(equipement);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> update(@PathVariable Long id, @RequestBody Equipement equipement) {
        Equipement existing = equipementService.getEquipementById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        equipement.setIdEquipement(id);
        Equipement updated = equipementService.updateEquipement(equipement);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Equipement existing = equipementService.getEquipementById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}