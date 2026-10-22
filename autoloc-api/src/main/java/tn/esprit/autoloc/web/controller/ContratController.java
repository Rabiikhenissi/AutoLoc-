package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContrat;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContrat contratService;

    @PostMapping
    public ResponseEntity<Contrat> addContrat(@RequestBody Contrat contrat) {
        Contrat saved = contratService.addContrat(contrat);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Contrat> getAll() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getById(@PathVariable Long id) {
        Contrat contrat = contratService.getContratById(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        Contrat existing = contratService.getContratById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().build();
        }
        Contrat updated = contratService.updateContrat(id, contrat);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Contrat existing = contratService.getContratById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        contratService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}