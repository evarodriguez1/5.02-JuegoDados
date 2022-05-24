package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.models.TiradasMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface TiradasRepositoryMongo extends MongoRepository<TiradasMongo, String> {

    ArrayList<TiradasMongo> findByIdJugador(String id);

}
