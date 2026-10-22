package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContrat {
    Contrat getContratById(Long id);
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Long id, Contrat contrat);
    void deleteById(Long id);
    List<Contrat> getAllContrats();
}