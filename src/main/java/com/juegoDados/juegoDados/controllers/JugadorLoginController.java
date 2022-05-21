package com.juegoDados.juegoDados.controllers;


import com.juegoDados.juegoDados.models.Jugador;
import com.juegoDados.juegoDados.models.JugadorLoginModel;
import com.juegoDados.juegoDados.models.JugadorMongo;
import com.juegoDados.juegoDados.services.JWTService;
import com.juegoDados.juegoDados.services.JugadorServiceMongo;
import com.juegoDados.juegoDados.services.JugadorServices;
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
    public ResponseEntity login(@RequestParam("user") String username) {
        try {
            //Jugador readUser = jugadorServices.findByEmail(username);
            JugadorMongo readUser = jugadorServiceMongo.findByEmail(username);
            String token = jwtService.getJWTToken(username);
            JugadorLoginModel user = new JugadorLoginModel();
            user.setUser(username);
            user.setToken(token);
            if(user.getUser().equals(readUser.getEmail())) {
                return (ResponseEntity.status(HttpStatus.OK))
                        .body(user);
            }else {
                return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                        .body("User no existe");
            }
        } catch(NullPointerException e){
            return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
                    .body("User no existe");

        }


    }

}
