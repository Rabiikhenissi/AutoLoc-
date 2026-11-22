package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehicule {

    private final IVehiculeRepository vehiculeRepo;
    private final IAgence agenceService;
    private final IEquipementRepository equipementRepo;

    @Override
    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepo.findById(id).orElse(null);
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepo.deleteById(id);
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepo.findAll();
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule v = vehiculeRepo.findById(idVehicule).orElse(null);
        Agence a = agenceService.getAgenceById(idAgence);
        if (v == null || a == null) return null;
        v.setAgence(a);
        return vehiculeRepo.save(v);
    }

    // ---- new (scenario 3) ----

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule v = vehiculeRepo.findById(idVehicule).orElse(null);
        Equipement eq = equipementRepo.findById(idEquipement).orElse(null);
        if (v == null || eq == null) return null;

        boolean dejaInstalle = v.getEquipements().stream()
                .anyMatch(e -> e.getIdEquipement().equals(idEquipement));
        if (dejaInstalle) {
            throw new IllegalStateException("Equipment is already installed on this vehicle.");
        }
        v.getEquipements().add(eq);
        return vehiculeRepo.save(v);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule v = vehiculeRepo.findById(idVehicule).orElse(null);
        if (v == null) return null;

        boolean retire = v.getEquipements()
                .removeIf(e -> e.getIdEquipement().equals(idEquipement));
        if (!retire) {
            throw new IllegalStateException("This equipment is not installed on this vehicle.");
        }
        return vehiculeRepo.save(v);
    }
}