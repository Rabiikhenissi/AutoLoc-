package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehicule;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehicule vehiculeService;

    @GetMapping
    public List<Vehicule> getAll() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicule);
    }

    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule vehicule) {
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().build();
        }
        Vehicule saved = vehiculeService.addVehicule(vehicule);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        Vehicule existing = vehiculeService.getVehiculeById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().build();
        }
        vehicule.setIdVehicule(id);
        Vehicule updated = vehiculeService.updateVehicule(vehicule);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Vehicule existing = vehiculeService.getVehiculeById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{idVehicule}/affecter/{idAgence}")
    public ResponseEntity<Vehicule> affecterVehicule(@PathVariable Long idVehicule,
                                                     @PathVariable Long idAgence) {
        Vehicule v = vehiculeService.affecterVehicule(idVehicule, idAgence);
        return v == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(v);
    }
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<Vehicule> affecterEquipement(@PathVariable Long idVehicule,
                                                       @PathVariable Long idEquipement) {
        try {
            Vehicule v = vehiculeService.affecterEquipement(idVehicule, idEquipement);
            return v == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(v);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();   // 409
        }
    }

    @DeleteMapping("/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<Vehicule> desaffecterEquipement(@PathVariable Long idVehicule,
                                                          @PathVariable Long idEquipement) {
        try {
            Vehicule v = vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
            return v == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(v);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();   // 409
        }
    }   
}