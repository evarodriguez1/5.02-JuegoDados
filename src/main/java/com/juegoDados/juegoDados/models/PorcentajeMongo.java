package com.juegoDados.juegoDados.models;

import lombok.Getter;
import lombok.Setter;

public class PorcentajeMongo implements  Comparable<PorcentajeMongo> {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private  String name;

    @Getter @Setter
    private  Double porcentaje;


    public PorcentajeMongo(String id, String name, Double percentaje) {
        this.id = id;
        this.name = name;
        this.porcentaje = percentaje;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int compareTo(PorcentajeMongo o) {

        return (porcentaje).compareTo(o.porcentaje);
    }



    @Override
    public String toString() {
        return "PorcentajeMongo [id=" + id + ", name=" + name + ", porcentaje=" + porcentaje + "]";
    }
}
