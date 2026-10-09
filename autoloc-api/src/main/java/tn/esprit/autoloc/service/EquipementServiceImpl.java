package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipement {

    private final IEquipementRepository equipementRepo;

    @Override
    public Equipement getEquipementById(Long id) {
        return equipementRepo.findById(id).orElse(null);
    }

    @Override
    public Equipement addEquipement(Equipement equipement) {
        return equipementRepo.save(equipement);
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {
        return equipementRepo.save(equipement);
    }

    @Override
    public void deleteEquipement(Long id) {
        equipementRepo.deleteById(id);
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepo.findAll();
    }
}