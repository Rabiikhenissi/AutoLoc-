package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgence {
    Agence getAgenceById(Long id);
    Agence addAgence(Agence agence);
    Agence updateAgence( Agence agence);
    void deleteAgence(Long id);
    List<Agence> getAllAgences();

}
