package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    public abstract Jugador findByEmail(String email);

}
