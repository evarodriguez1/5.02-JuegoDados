package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.JugadorMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JugadorRepositoryMongo extends MongoRepository<JugadorMongo, String> {

    JugadorMongo findByEmail(String email);
    JugadorMongo getById(String id);
}
