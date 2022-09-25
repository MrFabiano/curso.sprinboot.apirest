package com.springboot.curso.sprinboot.apirest.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ClientRepository extends MongoRepository<Client, String> {
}
