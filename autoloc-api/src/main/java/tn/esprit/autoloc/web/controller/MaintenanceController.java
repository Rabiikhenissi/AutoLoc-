package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenance;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenance maintenanceService;

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        if (maintenance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @PostMapping
    public ResponseEntity<Maintenance> addMaintenance(@RequestBody Maintenance maintenance) {
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            return ResponseEntity.badRequest().build();
        }
        Maintenance saved = maintenanceService.addMaintenance(maintenance);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        Maintenance existing = maintenanceService.getMaintenanceById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            return ResponseEntity.badRequest().build();
        }
        maintenance.setIdMaintenance(id);
        Maintenance updated = maintenanceService.updateMaintenance(maintenance);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Maintenance existing = maintenanceService.getMaintenanceById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}