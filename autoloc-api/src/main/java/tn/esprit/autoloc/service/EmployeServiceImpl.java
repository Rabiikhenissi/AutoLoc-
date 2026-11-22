package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmploye {

    private final IEmployeRepository employeRepo;
    private final IAgenceRepository agenceRepo;      // new

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepo.findById(id).orElse(null);
    }

    @Override
    public Employe addEmploye(Employe employe) {
        return employeRepo.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        return employeRepo.save(employe);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepo.deleteById(id);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepo.findAll();
    }

    // ---- new ----

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe e = employeRepo.findById(idEmploye).orElse(null);
        if (e == null) return null;
        e.setAgence(null);
        return employeRepo.save(e);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe e = employeRepo.findById(idEmploye).orElse(null);
        Agence a = agenceRepo.findById(idAgence).orElse(null);
        if (e == null || a == null) return null;

        if (e.getAgence() != null) {
            throw new IllegalStateException(
                    "Employee is already assigned to an agency: unassign first.");
        }
        e.setAgence(a);
        return employeRepo.save(e);
    }
}