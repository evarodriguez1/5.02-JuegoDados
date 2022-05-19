package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "users")
public class JugadorMongo {

    @Getter @Setter
    @Id
    private String id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Date date;

    public JugadorMongo() {

    }

    public JugadorMongo(String id, String nombre, String email, Date date) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.date = date;
    }

    @Override
    public String toString() {
        return "JugadorMongo [id=" + id + ", nombre=" + nombre + ", email=" + email + ", date=" + date + "]";
    }
}
