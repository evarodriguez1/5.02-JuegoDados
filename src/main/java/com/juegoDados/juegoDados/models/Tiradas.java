package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Game")
public class Tiradas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long idJugador;

    @Getter @Setter
    private int dado1;

    @Getter @Setter
    private int dado2;

    @Getter @Setter
    private String tiro;



    @Override
    public String toString() {
        return "GameModel [id=" + id + ", idJugador=" + idJugador + ", dado1=" + dado1 + ", dado2=" + dado2
                + ", tiro=" + tiro + "]";
    }


}
