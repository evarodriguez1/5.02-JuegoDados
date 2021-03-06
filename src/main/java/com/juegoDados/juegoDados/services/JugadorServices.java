package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service

public class JugadorServices {

    @Autowired
    private final JugadorRepository jugadorRepository;

    //contructor
    public JugadorServices (JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // sirve al controlador para crear un jugador
    public Jugador createUser(Jugador user) {
        return jugadorRepository.save(user);
    }

    //devuelve todos los jugadores
    public ArrayList<Jugador> readUsers(){
        return (ArrayList<Jugador>) jugadorRepository.findAll();
    }

    //guarda al jugador se usa para el update
    public Jugador saveUser(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    //encuentra al jugador por el email
    public Jugador findByEmail(String email) {
        return jugadorRepository.findByEmail(email);
    }

    //busca por el id
    public Jugador findById(Long id) {
        return jugadorRepository.getById(id);
    }

    //busca al jugador por el id, se utiliza en createGame y readGame
    public Optional<Jugador> findUserById(Long id) {
        return jugadorRepository.findById(id);
    }

    //hace verificaciones, primero busca x email,
    // pone anonimo al que no tiene nombre, y devuelve mensajes x si falta email,
    //no permite email repetido, se utiliza en createUser
    public String verifyUserData(Jugador user) {
        user.setDate(new Date());
        Jugador verifyUser;
        verifyUser = jugadorRepository.findByEmail(user.getEmail());
        if(verifyUser == null && user.getEmail() != null ) {
            if(user.getNombre() == null) {
                user.setNombre("anonimo");
            }
            return "creado";
        }else if(user.getEmail() == null) {
            return "Falta email";
        }else {
            return "el usuario ya existe";
        }

    }


}
