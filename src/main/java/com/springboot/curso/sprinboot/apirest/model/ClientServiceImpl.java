package com.springboot.curso.sprinboot.apirest.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableMongoRepositories
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> list() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client byCode(String codigo) {
        return this.clientRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Client no exist"));
    }

    @Override
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }
}
