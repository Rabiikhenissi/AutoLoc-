package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipement {
    Equipement getEquipementById(Long id);
    Equipement addEquipement(Equipement equipement);
    Equipement updateEquipement(Equipement equipement);
    void deleteEquipement(Long id);
    List<Equipement> getAllEquipements();
}