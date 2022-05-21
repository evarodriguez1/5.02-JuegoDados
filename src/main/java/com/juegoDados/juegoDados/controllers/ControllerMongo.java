package com.juegoDados.juegoDados.controllers;

import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.services.JugadorServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/juegoMongo")
public class ControllerMongo {

    @Autowired
    JugadorServiceMongo jugadorServiceMongo;

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



}
