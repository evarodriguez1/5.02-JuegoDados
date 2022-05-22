package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.TiradasMongo;
import com.juegoDados.juegoDados.repositories.TiradasRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiradasServiceMongo {

    @Autowired
    TiradasRepositoryMongo tiradasRepositoryMongo;

    public TiradasMongo createGame(TiradasMongo game) {
        return tiradasRepositoryMongo.save(game);
    }




}
