package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmploye {
    Employe getEmployeById(Long id);
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    void deleteEmploye(Long id);
    List<Employe> getAllEmployes();
}