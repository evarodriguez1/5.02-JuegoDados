package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.repositories.JugadorRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class JugadorServiceMongo {

    @Autowired
    JugadorRepositoryMongo jugadorRepositoryMongo;

    //crea un jugador
    public JugadorMongo createUser(JugadorMongo jugador) {
        return jugadorRepositoryMongo.save(jugador);
    }

    //devuelve la lista de jugadores
    public ArrayList<JugadorMongo> readUsers () {
        return (ArrayList<JugadorMongo>) jugadorRepositoryMongo.findAll();
    }
/*
    //busca jugador por el id con el Optional
    public Optional<JugadorMongo> findUserById(String id) {
        return jugadorRepositoryMongo.findById(id);
    }
 */
    //busca jugador por email
    public JugadorMongo findByEmail(String email) {
        return jugadorRepositoryMongo.findByEmail(email);
    }

    //busca por id
    public JugadorMongo getById(String id) {
        return jugadorRepositoryMongo.getById(id);
    }

    //comprueba si el jugador existe
    public boolean existe(String id) {
        boolean existe = false;

        for (JugadorMongo jm:readUsers()) {
            if(id.equals(jm.getId())){
                existe=true;
            }
        }
        return existe;
    }
/*
    //guarda al jugador
    public JugadorMongo saveUser(JugadorMongo jugador) {
        return jugadorRepositoryMongo.save(jugador);
    }

 */
    //verifica la data del jugador
    public String verifyUserData(JugadorMongo jugador) {
        jugador.setDate(new Date());
        JugadorMongo verifyUser;
        verifyUser = jugadorRepositoryMongo.findByEmail(jugador.getEmail());
        if(verifyUser == null && jugador.getEmail() != null ) {
            if(jugador.getNombre() == null) {
                jugador.setNombre("anonimo");
            }
            return "creadoMongo";
        }else if(jugador.getEmail() == null) {
            return "Falta email";
        }else {
            return "User exist";
        }

    }
}
