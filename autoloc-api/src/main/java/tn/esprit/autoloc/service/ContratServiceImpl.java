package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContrat {

    private final IContratRepository contratRepo;

    @Override
    public Contrat getContratById(Long id) {
        return contratRepo.findById(id).orElse(null);
    }

    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepo.save(contrat);
    }

    @Override
    public Contrat updateContrat(Contrat contrat) {
        return contratRepo.save(contrat);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepo.deleteById(id);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepo.findAll();
    }
}