package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClient {

    private final IClientRepository clientRepo;

    @Override
    public Client getClientById(Long id) {
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }
}