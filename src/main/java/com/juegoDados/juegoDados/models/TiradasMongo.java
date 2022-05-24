package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public class TiradasMongo {

    @Getter
    @Setter
    @Id
    private String id;

    @Getter @Setter
    private String idJugador;

    @Getter @Setter
    private int dado1;

    @Getter @Setter
    private int dado2;

    @Getter @Setter
    private String tiro;

    @Override
    public String toString() {
        return "TiradasMongo [id=" + id + ", idJugador=" + idJugador + ", dado1=" + dado1 + ", dado2=" + dado2
                + " tiro=" + tiro + "]";
    }

}
