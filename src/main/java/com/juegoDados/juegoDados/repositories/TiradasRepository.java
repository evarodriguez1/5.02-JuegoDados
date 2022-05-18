package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.Tiradas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TiradasRepository extends JpaRepository<Tiradas, Long> {

    ArrayList<Tiradas> findByIdJugador(Long idJugador);

}
