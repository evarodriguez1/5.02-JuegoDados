package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

    @Entity
    @Table(name = "Jugador")
    public class Jugador {

        @Id
        @Getter @Setter
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @Getter @Setter
        private String nombre;

        @Getter @Setter
        private String email;

        @Getter @Setter
        private Date date;

        public Jugador(Long id, String nombre, String email, Date date) {

            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.date = date;
        }

        public Jugador() {

        }

        @Override
        public String toString() {
            return "Jugador [id=" + id + ", nombre=" + nombre + ", email=" + email + ", date=" + date + "]";
        }

}
