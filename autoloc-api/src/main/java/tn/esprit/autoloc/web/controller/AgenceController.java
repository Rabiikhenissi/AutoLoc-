package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgence;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgence agenceService;

    @GetMapping
    public List<Agence> getAll() {
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getById(@PathVariable Long id) {
        Agence agence = agenceService.getAgenceById(id);
        if (agence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agence);
    }

    @PostMapping
    public ResponseEntity<Agence> addAgence(@RequestBody Agence agence) {
        if (agence == null || agence.getNom() == null || agence.getNom().trim().isEmpty() ||
                agence.getAdresse() == null || agence.getAdresse().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Agence saved = agenceService.addAgence(agence);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence agence) {
        Agence existing = agenceService.getAgenceById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (agence.getNom() == null || agence.getNom().trim().isEmpty() ||
                agence.getAdresse() == null || agence.getAdresse().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        agence.setIdAgence(id);
        Agence updated = agenceService.updateAgence(agence);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Agence existing = agenceService.getAgenceById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        agenceService.deleteAgence(id);
        return ResponseEntity.noContent().build();
    }
}