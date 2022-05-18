package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;

public class Porcentaje implements Comparable<Porcentaje>{

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Double porcentaje;

    //contructor
    public Porcentaje(Long id, String name, Double porcentaje) {
        this.id = id;
        this.name = name;
        this.porcentaje = porcentaje;
    }

    //este metodo compara los dos porcentajes
    @SuppressWarnings("deprecation")
    @Override
    public int compareTo(Porcentaje o) {

        return porcentaje.compareTo(o.porcentaje);
    }

    @Override
    public String toString() {
        return "Porcentaje [id=" + id + ", name=" + name + ", porcentaje=" + porcentaje + "]";
    }



}
