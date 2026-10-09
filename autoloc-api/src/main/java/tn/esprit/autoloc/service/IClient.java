package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClient {
    Client getClientById(Long id);
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
    List<Client> getAllClients();
}