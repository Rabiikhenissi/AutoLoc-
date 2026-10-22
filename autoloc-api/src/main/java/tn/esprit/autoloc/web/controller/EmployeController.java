package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmploye;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmploye employeService;

    @GetMapping
    public List<Employe> getAll() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getById(@PathVariable Long id) {
        Employe employe = employeService.getEmployeById(id);
        if (employe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employe);
    }

    @PostMapping
    public ResponseEntity<Employe> addEmploye(@RequestBody Employe employe) {
        if (employe == null || employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Employe saved = employeService.addEmploye(employe);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe employe) {
        Employe existing = employeService.getEmployeById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        employe.setIdEmploye(id);
        Employe updated = employeService.updateEmploye(employe);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Employe existing = employeService.getEmployeById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}