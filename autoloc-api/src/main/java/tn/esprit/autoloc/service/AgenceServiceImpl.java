package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AgenceServiceImpl implements IAgence {

    private final IAgenceRepository agenceRepo;

    @Override
    public Agence getAgenceById(Long id) {
        return agenceRepo.findById(id).orElse(null);
    }

    @Override
    public Agence addAgence(Agence agence) {
        return agenceRepo.save(agence);
    }

    @Override
    public Agence updateAgence( Agence agence) {
        return agenceRepo.save(agence);

    }

    @Override
    public void deleteAgence(Long id) {
        agenceRepo.deleteById(id);

    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepo.findAll();
    }
}
