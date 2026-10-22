package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservation;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservation reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null &&
                reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            return ResponseEntity.badRequest().build();
        }
        Reservation saved = reservationService.addReservation(reservation);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation existing = reservationService.getReservationById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null &&
                reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            return ResponseEntity.badRequest().build();
        }
        reservation.setIdReservation(id);
        Reservation updated = reservationService.updateReservation(reservation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Reservation existing = reservationService.getReservationById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}