package com.juegoDados.juegoDados.controllers;


import com.juegoDados.juegoDados.models.JugadorLogin;
import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.services.JWTService;
import com.juegoDados.juegoDados.services.JugadorServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginJugador")
public class JugadorLoginController {

    @Autowired
    JWTService jwtService;
    @Autowired
    //JugadorServices jugadorServices;
    JugadorServiceMongo jugadorServiceMongo;

    @PostMapping()
    public ResponseEntity login(@RequestParam("user") String nombreJugador) {
        try {
            //Jugador jugadorDatos = jugadorServices.findByEmail(nombreJugador);
            JugadorMongo jugadorDatos = jugadorServiceMongo.findByEmail(nombreJugador);
            String token = jwtService.getJWTToken(nombreJugador);
            JugadorLogin jugador = new JugadorLogin();
            jugador.setUser(nombreJugador);
            jugador.setToken(token);
            if(jugador.getUser().equals(jugadorDatos.getEmail())) {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body(jugador);
            }else {
                return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                        .body("Jugador no existe");
            }
        } catch(NullPointerException e){
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body("Jugador no existe");

        }


    }

}
