package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.repositories.JugadorRepository;
import lombok.AllArgsConstructor;
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

    //encuentra al jugador por el id
    public Optional<Jugador> findUserById(Long id) {
        return jugadorRepository.findById(id);
    }

    //encuentra por el email
    public Jugador findByEmail(String email) {
        return jugadorRepository.findByEmail(email);
    }

    //guarda al jugador se usa para el update
    public Jugador saveUser(Jugador user) {
        return jugadorRepository.save(user);
    }

    //busca un jugador por el id
    public Jugador findById(Long id) {
        return jugadorRepository.getById(id);
    }

    //hace verificaciones, primero busca x email,
    // pone anonimo al que no tiene nombre, y devuelve mensajes x si falta email,
    //no permiite nombre repetido
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
