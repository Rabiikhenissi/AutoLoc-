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
        if (contrat.getPaiements() != null) {
            contrat.getPaiements().forEach(p -> p.setContrat(contrat));
        }
        return contratRepo.save(contrat);
    }


    @Override
    public Contrat updateContrat(Long id, Contrat contratDetails) {

        Contrat existing = contratRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));

        if (contratDetails.getMontantTotal() != null) {
            existing.setMontantTotal(contratDetails.getMontantTotal());
        }

        if (contratDetails.getDateSignature() != null) {
            existing.setDateSignature(contratDetails.getDateSignature());
        }

        if (contratDetails.getValide() != null) {
            existing.setValide(contratDetails.getValide());
        }

        if (contratDetails.getReservation() != null) {
            existing.setReservation(contratDetails.getReservation());
        }

        return contratRepo.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        contratRepo.deleteById(id);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepo.findAll();
    }
}