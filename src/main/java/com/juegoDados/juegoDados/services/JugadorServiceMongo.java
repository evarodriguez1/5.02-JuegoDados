package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.repositories.JugadorRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceMongo {

    @Autowired
    JugadorRepositoryMongo jugadorRepositoryMongo;

    public JugadorMongo createUser(JugadorMongo jugador) {
        return jugadorRepositoryMongo.save(jugador);
    }

    //devuelve la lista de jugadores
    public List<JugadorMongo> readUsers () {
        return jugadorRepositoryMongo.findAll();
    }

    //busca jugador por el id
    public Optional<JugadorMongo> findUserById(String id) {
        return jugadorRepositoryMongo.findById(id);
    }

    //busca jugador por email
    public JugadorMongo findByEmail(String email) {
        return jugadorRepositoryMongo.findByEmail(email);
    }

    //guarda al jugador
    public JugadorMongo saveUser(JugadorMongo user) {
        return jugadorRepositoryMongo.save(user);
    }

    //busca por id
    public JugadorMongo findById(String id) {
        return jugadorRepositoryMongo.getById(id);
    }

    //verifica la data del jugador
    public String verifyUserData(JugadorMongo user) {
        user.setDate(new Date());
        JugadorMongo verifyUser;
        verifyUser = jugadorRepositoryMongo.findByEmail(user.getEmail());
        if(verifyUser == null && user.getEmail() != null ) {
            if(user.getNombre() == null) {
                user.setNombre("anonimo");
            }
            return "creadoMongo";
        }else if(user.getEmail() == null) {
            return "Falta email";
        }else {
            return "User exist";
        }

    }
}
