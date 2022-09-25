package com.springboot.curso.sprinboot.apirest.model;

import java.util.List;

public interface ClientService {

    public List<Client> list();
    public Client byCode(String codigo);
    public Client save(Client client);
}
