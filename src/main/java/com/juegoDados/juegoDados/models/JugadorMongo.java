package com.juegoDados.juegoDados.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "users")
public class JugadorMongo {
    @Id
    private String id;
    private String nombre;
    private String email;
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
        return "UserModelMongo [id=" + id + ", nombre=" + nombre + ", email=" + email + ", date=" + date + "]";
    }
}
