package com.sanvalero.ejemploTaller.domain;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Creado por @author: Javier
 * el 28/10/2020
 */
public class Coche {

    private String matricula;
    private String marca;
    private String modelo;
    private String tipo;

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche(String matricula){
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return matricula + "-" + marca + "-" + modelo + "-" + tipo;
    }

}
