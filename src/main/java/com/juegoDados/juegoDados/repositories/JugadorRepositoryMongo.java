package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.JugadorMongo;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface JugadorRepositoryMongo extends MongoRepository<JugadorMongo, String> {


}