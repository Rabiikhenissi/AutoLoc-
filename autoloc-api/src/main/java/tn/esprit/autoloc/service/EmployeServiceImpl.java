package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmploye {

    private final IEmployeRepository employeRepo;

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
}