package com.juegoDados.juegoDados.controllers;

import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.models.Porcentaje;
import com.juegoDados.juegoDados.models.Tiradas;
import com.juegoDados.juegoDados.services.JugadorServices;
import com.juegoDados.juegoDados.services.TiradasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class JugadorController {

    @Autowired
    JugadorServices jugadorServices;

    @Autowired
    TiradasServices tiradasServices;

    //crea un jugador
    @PostMapping()
    public ResponseEntity createUser(@RequestBody Jugador jugador) {

        String mensaje = jugadorServices.verifyUserData(jugador);
        if(mensaje.equals("creado")) {
            jugadorServices.createUser(jugador);
            return (ResponseEntity.status(HttpStatus.OK))
                    .body(jugador);
        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body(mensaje);
        }
    }


    //actualiza el nombre del jugador
    @PutMapping(path = "/{id}")
    public ResponseEntity updateUserName(@RequestBody Jugador jugadorEncontrado, @PathVariable Long id) {
        Jugador jugador = jugadorServices.findById(id);
        if(jugador != null) {
            String nombreNuevo = jugadorEncontrado.getNombre();
            jugador.setNombre(nombreNuevo);
            jugadorServices.saveUser(jugador);
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("Actualizado");
        }else {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("No existe el usuario");
        }

    }

    //crea una partida de un jugador especifico (id)
    @PostMapping(path="/{id}/games")
    public ResponseEntity createGames(@RequestBody Tiradas juego, @PathVariable("id") Long id) {

        Optional<Jugador> jugador = jugadorServices.findUserById(id);

        if(!jugador.isEmpty()) {
            boolean ok = tiradasServices.verifyGameData(juego);
            if(juego != null && id != null && ok == true) {
                String porcentajeExito = "";
                porcentajeExito = tiradasServices.porcentaje(juego);
                juego.setTiro(porcentajeExito);
                juego.setIdJugador(id);
                tiradasServices.createGame(juego);
                return (ResponseEntity.status(HttpStatus.OK))
                        .body(juego);
            }else if(id == null){
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("no se encuentra el id");
            }else if(ok == false) {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("jugador sin puntaje");
            }else {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body("Faltan datos");
            }
        }else {
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST)).
                    body("no existe el usario del id");
        }

    }

    //devuelve la lista de todos los jugadores
    @GetMapping()
    public ResponseEntity readUsers() {

        ArrayList<Jugador> jugadores = jugadorServices.readUsers();
        ArrayList <Porcentaje> puntos;
        puntos = tiradasServices.jugadorPorcentaje(jugadores);

        if(puntos != null) {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body(puntos);
        }else {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("No existen usuarios registrados");
        }
    }

    //devuelve todas las partidas de un jugador
    @GetMapping(path = "/{id}/games")
    public ResponseEntity readGames(@PathVariable("id") Long id) {
        //leer token, comparar con el id que se ingresa
        Optional<Jugador> jugador;
        ArrayList<Tiradas> partidas;
        jugador = jugadorServices.findUserById(id);
        if(jugador.isEmpty()) {
            return (ResponseEntity.status(HttpStatus.OK))
                    .body("Este usuario no se ha registrado");
        }else {
            partidas = tiradasServices.findByUserId(id);
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
        ArrayList<Jugador> jugadores;
        jugadores = jugadorServices.readUsers();
        ArrayList<Porcentaje> ranking = new ArrayList<>();
        ranking = tiradasServices.ranking(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(ranking);
    }

    //devuelve al jugador que mas veces perdió
    @GetMapping(path = "ranking/loser")
    public ResponseEntity readLoserGamer() {

        ArrayList<Jugador> jugadores;
        jugadores = jugadorServices.readUsers();
        Porcentaje perdedor;
        perdedor = tiradasServices.peoresJugadores(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(perdedor);

    }

    //devuelve al jugador que mas veces ganó
    @GetMapping(path = "ranking/winner")
    public ResponseEntity readWinnerGamer() {

        ArrayList<Jugador> jugadores;
        jugadores = jugadorServices.readUsers();
        Porcentaje ganador;
        ganador = tiradasServices.mejoresJugadores(jugadores);

        return (ResponseEntity.status(HttpStatus.OK))
                .body(ganador);

    }

}
