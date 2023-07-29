package com.enrike.lectorcarnet;

public class Persona {
    private String Id;
    private String Nombre;
    private String Carrera;
    private String Entrada;
    private String Salida;


    public Persona(){
    }

    public Persona(String id, String nombre, String carrera, String entrada, String salida){
        Id = id;
        Nombre = nombre;
        Carrera = carrera;
        Entrada = entrada;
        Salida = salida;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String carrera) {
        Carrera = carrera;
    }

    public String getEntrada() {
        return Entrada;
    }

    public void setEntrada(String entrada) {
        Entrada = entrada;
    }

    public String getSalida() {
        return Salida;
    }

    public void setSalida(String salida) {
        Salida = salida;
    }
}
