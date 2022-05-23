package com.juegoDados.juegoDados.services;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.models.PorcentajeMongo;
import com.juegoDados.juegoDados.models.TiradasMongo;
import com.juegoDados.juegoDados.repositories.TiradasRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class TiradasServiceMongo {

    @Autowired
    TiradasRepositoryMongo tiradasRepositoryMongo;

    public TiradasMongo createGame(TiradasMongo tirada) {
        return tiradasRepositoryMongo.save(tirada);
    }

    public ArrayList<TiradasMongo> findByIdJugador(String id){
        return tiradasRepositoryMongo.findByIdJugador(id);
    }

    //busca por id
    public JugadorMongo findById(String id) {
        return tiradasRepositoryMongo.getById(id);
    }

    public ArrayList<TiradasMongo> readUser(){
        return (ArrayList<TiradasMongo>) tiradasRepositoryMongo.findAll();
    }


    public ArrayList<PorcentajeMongo> userPorcentaje(ArrayList<JugadorMongo> jugadores){

        ArrayList<PorcentajeMongo> porcentajeJugador = new ArrayList<PorcentajeMongo>();
        ArrayList<TiradasMongo> tiradasJugador = null;
        double suma = 0;
        double porcentaje = 0;
        for(int i = 0; i < jugadores.size(); i++) {
            tiradasJugador = findByIdJugador(jugadores.get(i).getId());
            for(int j = 0; j < tiradasJugador.size(); j++) {
                if(tiradasJugador.get(j).getTiro().equals("gano")) {
                    suma = suma + 1;
                }
            }
            porcentaje = suma / tiradasJugador.size();
            PorcentajeMongo datosJugador = new PorcentajeMongo(null, null, null);
            datosJugador.setId(jugadores.get(i).getId());
            datosJugador.setName(jugadores.get(i).getNombre());
            datosJugador.setPorcentaje(porcentaje);
            porcentajeJugador.add(i, datosJugador);
            suma = 0;
            tiradasJugador = null;

        }
        return porcentajeJugador;
    }

    public ArrayList<PorcentajeMongo> ranking(ArrayList<JugadorMongo> jugadores) {
        ArrayList<PorcentajeMongo> jugadorPorcentajeRnking;
        jugadorPorcentajeRnking =  userPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRnking);
        return jugadorPorcentajeRnking;
    }


    public PorcentajeMongo peorJugador(ArrayList<JugadorMongo> jugadores) {

        ArrayList<PorcentajeMongo> jugadorPorcentajeRnking;
        jugadorPorcentajeRnking =  userPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRnking);
        return jugadorPorcentajeRnking.get(0);

    }

    public PorcentajeMongo mejorJugador(ArrayList<JugadorMongo> jugadores) {

        ArrayList<PorcentajeMongo> jugadorPorcentajeRnking;
        jugadorPorcentajeRnking =  userPorcentaje(jugadores);
        Collections.sort(jugadorPorcentajeRnking);
        return jugadorPorcentajeRnking.get(jugadorPorcentajeRnking.size()-1);
    }

    public boolean verifyGameData(TiradasMongo tirada) {
        boolean ok = true;
        if(tirada.getDado1() >= 0 && tirada.getDado1()<= 7 && tirada.getDado2() >= 0 && tirada.getDado2() <= 7) {
            return ok;
        }else {
            ok = false;
            return ok;
        }

    }

    public String porcentaje(TiradasMongo tirada) {

        int tiro1 = tirada.getDado1();
        int tiro2 = tirada.getDado2();
        int sum = tiro1 + tiro2;
        String porcentaje = "perdio";

        if(sum == 7) {
            porcentaje = "gano";
        }else {
            return porcentaje;
        }

        return porcentaje;
    }


}
