package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiement;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiement paiementService;

    @GetMapping
    public ResponseEntity<List<Paiement>> findAll() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> findById(@PathVariable Long id) {
        Paiement paiement = paiementService.getPaiementById(id);

        if (paiement == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(paiement);
    }

    @PostMapping
    public ResponseEntity<Paiement> create(@RequestBody Paiement paiement) {
        Paiement savedPaiement = paiementService.addPaiement(paiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaiement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(@PathVariable Long id,
                                           @RequestBody Paiement paiement) {

        Paiement existingPaiement = paiementService.getPaiementById(id);

        if (existingPaiement == null) {
            return ResponseEntity.notFound().build();
        }

        paiement.setIdPaiement(id);

        Paiement updatedPaiement = paiementService.updatePaiement(paiement);

        return ResponseEntity.ok(updatedPaiement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Paiement existingPaiement = paiementService.getPaiementById(id);

        if (existingPaiement == null) {
            return ResponseEntity.notFound().build();
        }

        paiementService.deletePaiement(id);

        return ResponseEntity.noContent().build();
    }
}