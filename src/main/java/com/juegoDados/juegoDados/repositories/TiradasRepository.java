package com.juegoDados.juegoDados.repositories;

import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.models.Tiradas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TiradasRepository extends JpaRepository<Tiradas, Long> {

    //se usa en el findById servicioTiradas, y en el controller ReadGames para devolver las tiradas del jugador x
    ArrayList<Tiradas> findByIdJugador(Long idJugador);

}
