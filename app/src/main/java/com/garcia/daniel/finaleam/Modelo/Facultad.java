package com.garcia.daniel.finaleam.Modelo;

public class Facultad {
    private String nombre;
    private int codigo;
    private int codigoPrograma;

    public Facultad() {
    }

    public Facultad(String nombre, int codigo, int codigoPrograma) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoPrograma = codigoPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(int codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
}
