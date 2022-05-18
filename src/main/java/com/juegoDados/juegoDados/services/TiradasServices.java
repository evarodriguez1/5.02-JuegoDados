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

    public Tiradas createGame(Tiradas game) {
        return tiradasRepository.save(game);
    }

    //busca al jugador por el id
    public ArrayList<Tiradas> findByUserId(Long id){
        return tiradasRepository.findByIdJugador(id);
    }

    public ArrayList<Tiradas> readUser(){
        return (ArrayList<Tiradas>) tiradasRepository.findAll();
    }


    //calcula el porcentaje de cada jugador
    public ArrayList<Porcentaje> jugadorPorcentaje (ArrayList<Jugador> jugadores){
        ArrayList<Porcentaje> jugadorPorcentaje = new ArrayList<Porcentaje>();
        ArrayList<Tiradas> jugadorTiradas = null;
        double suma = 0;
        double porcentaje = 0;
        for(int i = 0; i < jugadores.size(); i++) {
            jugadorTiradas = findByUserId(jugadores.get(i).getId());
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

    //devuelve una lista con el ranking de todos los jugadores
    public ArrayList<Porcentaje> ranking (ArrayList<Jugador> jugadores) {
        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking;
    }

    //devuelve una lista coon los peores jugadores
    public Porcentaje peoresJugadores (ArrayList<Jugador> jugadores) {

        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking.get(0);
    }

    //devuelve una lista con los mejores jugadores
    public Porcentaje mejoresJugadores (ArrayList<Jugador> jugadores) {

        ArrayList<Porcentaje> jugadorPorcentajeRanking = new ArrayList<Porcentaje>();
        jugadorPorcentajeRanking =  jugadorPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRanking);
        return jugadorPorcentajeRanking.get(jugadorPorcentajeRanking.size()-1);
    }

    //verifica que sean valores correctos en los lanzamientos
    public boolean verifyGameData(Tiradas juego) {
        boolean ok = true;
        if(juego.getDado1() >= 0 && juego.getDado1() <= 7 && juego.getDado2() >= 0 && juego.getDado2() <= 7) {
            return ok;
        }else {
            ok = false;
            return ok;
        }

    }

    //logica de juego, si suma 7 gana, sino pierde
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
