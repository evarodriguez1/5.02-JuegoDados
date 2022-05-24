package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.models.Porcentaje;
import com.juegoDados.juegoDados.models.Tiradas;
import com.juegoDados.juegoDados.repositories.TiradasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class TiradasServices {

    @Autowired
    TiradasRepository tiradasRepository;

    //crea una partida
    public Tiradas createGame(Tiradas game) {
        return tiradasRepository.save(game);
    }

    //elimina las partidas de un jugador
    public void deleteTiradas(Long id){
        tiradasRepository.deleteAll(findByIdJugador(id));

    }
/*
    //devuelve las tiradas de un jugador
    public ArrayList<Tiradas> readUser(){
        return (ArrayList<Tiradas>) tiradasRepository.findAll();
    }
 */
    //devuelve una lista ordenada (en funcion del porcentaje descendente) con el ranking de todos los jugadores
    public ArrayList<Porcentaje> ranking (ArrayList<Jugador> jugadores) {
        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking;
    }

    //devuelve el peor jugador
    public Porcentaje peoresJugadores (ArrayList<Jugador> jugadores) {

        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking.get(0);
    }

    //devuelve el mejor jugador
    public Porcentaje mejoresJugadores (ArrayList<Jugador> jugadores) {

        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking.get(jugadorPorcentajeRanking.size()-1);
    }

    //busca al jugador por el id se utiliza en el ReadGames del controller
    public ArrayList<Tiradas> findByIdJugador(Long id){
        return tiradasRepository.findByIdJugador(id);
    }

    //calcula el porcentaje del jugador con id x
    public ArrayList<Porcentaje> jugadorPorcentaje (ArrayList<Jugador> jugadores){
        ArrayList<Porcentaje> jugadorPorcentaje = new ArrayList<Porcentaje>();
        ArrayList<Tiradas> jugadorTiradas;
        double suma = 0;
        double porcentaje;
        for(int i = 0; i < jugadores.size(); i++) {
            jugadorTiradas = findByIdJugador(jugadores.get(i).getId());
            for(int j = 0; j < jugadorTiradas.size(); j++) {
                if(jugadorTiradas.get(j).getTiro().equals("gano")) {
                    suma = suma + 1;
                }
            }
            porcentaje = suma / jugadorTiradas.size();
            Porcentaje datosJugador = new Porcentaje(null, null, null);
            datosJugador.setId(jugadores.get(i).getId());
            datosJugador.setName(jugadores.get(i).getNombre());
            datosJugador.setPorcentaje(porcentaje);
            jugadorPorcentaje.add(i, datosJugador);
            suma = 0;
        }
        return jugadorPorcentaje;
    }

    //verifica que sean valores correctos en los lanzamientos, se utiliza en createGame
    public boolean verifyGameData(Tiradas juego) {
        boolean ok = true;
        if(juego.getDado1() >= 0 && juego.getDado1() <= 7 && juego.getDado2() >= 0 && juego.getDado2() <= 7) {
            return ok;
        }else {
            ok = false;
            return ok;
        }

    }

    //logica de juego, si suma 7 gana, sino pierde, se utiliza en createGame
    public String porcentaje(Tiradas juego) {

        int tiroUno = juego.getDado1();
        int tiroDos = juego.getDado2();
        int sum = tiroUno + tiroDos;
        String porcentaje = "perdio";

        if(sum == 7) {
            porcentaje = "gano";
        }else {
            return porcentaje;
        }

        return porcentaje;
    }

}
