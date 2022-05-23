package com.juegoDados.juegoDados.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@Document(collection = "JugadorMongo")
public class JugadorMongo {

    @Getter @Setter
    @Id
    @Field(name="id")
    private String id;

    @Field(name="nombre")
    @Getter @Setter
    private String nombre;

    @Field(name="email")
    @Getter @Setter
    private String email;

    @Field(name="date")
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
