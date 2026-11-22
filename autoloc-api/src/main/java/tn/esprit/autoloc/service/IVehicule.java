package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehicule {
    Vehicule getVehiculeById(Long id);
    Vehicule addVehicule(Vehicule vehicule);
    Vehicule updateVehicule(Vehicule vehicule);
    void deleteVehicule(Long id);
    List<Vehicule> getAllVehicules();
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}