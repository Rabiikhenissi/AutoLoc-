package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiement {

    private final IPaiementRepository paiementRepo;

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepo.findById(id).orElse(null);
    }

    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepo.deleteById(id);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepo.findAll();
    }
}