package com.juegoDados.juegoDados.controllers;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.models.PorcentajeMongo;
import com.juegoDados.juegoDados.models.TiradasMongo;
import com.juegoDados.juegoDados.services.JugadorServiceMongo;
import com.juegoDados.juegoDados.services.TiradasServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/juegoMongo")
public class ControllerMongo {

    @Autowired
    JugadorServiceMongo jugadorServiceMongo;

    @Autowired
    TiradasServiceMongo tiradasServiceMongo;

    //crea un jugador
    @PostMapping()
    public ResponseEntity createUser(@RequestBody JugadorMongo jugadorMongo) {
        String mensaje = jugadorServiceMongo.verifyUserData(jugadorMongo);
        if(mensaje.equals("creadoMongo")) {
            jugadorServiceMongo.createUser(jugadorMongo);
            return (ResponseEntity.status(HttpStatus.OK))
                    .body(jugadorMongo);
        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body(mensaje);
        }
    }

    //actualiza el nombre del jugador
    @PutMapping(path = "/{id}")
    public ResponseEntity updateUserName(@RequestBody JugadorMongo jugadorEncontrado, @PathVariable("id") String id) {

        JugadorMongo jugadorMongo = jugadorServiceMongo.getById(id);
        if(jugadorServiceMongo.existe(id)){
            jugadorMongo.setNombre(jugadorEncontrado.getNombre());
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("Actualizado");

        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body("No existe el jugador");
        }

    }

    //devuelve la lista de todos los jugadores
    @GetMapping()
    public  ResponseEntity readUsers() {

        ArrayList <JugadorMongo> jugadores = jugadorServiceMongo.readUsers();
        ArrayList<PorcentajeMongo> puntajeJugador;
        puntajeJugador = tiradasServiceMongo.userPorcentaje(jugadores);
        if(jugadores != null) {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body(puntajeJugador);
        }else {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("No existen usuarios registrados");
        }
    }

    //elimina las partidas de un jugador
    @DeleteMapping(path = "/{id}/games")
    public ResponseEntity deleteTiradas (@PathVariable ("id") String id){
        JugadorMongo jugador = jugadorServiceMongo.getById(id);
        if(jugador != null) {
            tiradasServiceMongo.deleteTiradas(id);
            return ResponseEntity.status(HttpStatus.OK).body("Partidas eliminadas");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra");
        }
    }

    //crea una partida de un jugador especifico (id)
    @PostMapping(path="/{id}/games")
    public ResponseEntity createGames(@RequestBody TiradasMongo tirada, @PathVariable String id) {

        JugadorMongo jugador = jugadorServiceMongo.getById(id);

        if(jugador == null) {
            boolean ok = tiradasServiceMongo.verifyGameData(tirada);
            if(tirada != null && ok == true && id != null) {
                String porcentajeExito = "";
                porcentajeExito = tiradasServiceMongo.porcentaje(tirada);
                tirada.setTiro(porcentajeExito);
                tirada.setIdJugador(id);
                tiradasServiceMongo.createGame(tirada);
                return (ResponseEntity.status(HttpStatus.OK))
                        .body(tirada);
            }else if(id == null){
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("No se encuentra ese id");
            }else if(ok == false) {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("jugador sin puntaje");
            }else {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("Faltan datos");
            }
        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST)).
                    body("no existe el jugador");
        }
    }

    //devuelve todas las partidas de un jugador
    @GetMapping(path = "/{id}/games")
    public ResponseEntity readGames(@PathVariable("id") String id) {

        JugadorMongo jugador;
        ArrayList<TiradasMongo> partidas;
        jugador = jugadorServiceMongo.getById(id);

        if(jugador != null) {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("este usuario no se ha registrado");
        }else {
            partidas = tiradasServiceMongo.findByIdJugador(id);
            if(partidas.size() > 0) {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body(partidas);
            }else {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("no existen partidas de este usuario");
            }

        }
    }

    //devuelve una lista ordenada con el ranking(de menor a mayor según el porcentaje de exito)
    @GetMapping(path = "ranking")
    public ResponseEntity readRanking() {

        ArrayList<JugadorMongo> jugadores;
        jugadores = (ArrayList<JugadorMongo>) jugadorServiceMongo.readUsers();
        ArrayList<PorcentajeMongo> ranking = new ArrayList<>();
        ranking = tiradasServiceMongo.ranking(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(ranking);
    }

    //devuelve al jugador que mas veces perdió
    @GetMapping(path = "ranking/perdedor")
    public ResponseEntity readLoserGamer() {

        ArrayList<JugadorMongo> jugadores;
        jugadores = (ArrayList<JugadorMongo>) jugadorServiceMongo.readUsers();
        PorcentajeMongo perdedor;
        perdedor = tiradasServiceMongo.peorJugador(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(perdedor);

    }

    //devuelve al jugador que mas veces ganó
    @GetMapping(path = "ranking/ganador")
    public ResponseEntity readWinnerGamer() {

        ArrayList<JugadorMongo> jugadores;
        jugadores = (ArrayList<JugadorMongo>) jugadorServiceMongo.readUsers();
        PorcentajeMongo ganador;
        ganador = tiradasServiceMongo.mejorJugador(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(ganador);

    }

}
