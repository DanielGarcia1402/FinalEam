package com.garcia.daniel.finaleam.Modelo;

public class Usuario {
    private String nombres;
    private String apellidos;
    private int cedula;
    private int codigoEstudiante;
    private String facultad;
    private String programa;
    private String fechaNacimiento;
    private String correo;
    private String password;
    private int estado;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, int cedula, int codigoEstudiante,
                   String facultad, String programa, String fechaNacimiento,
                   String correo, String password, int estado, String rol) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.codigoEstudiante = codigoEstudiante;
        this.facultad = facultad;
        this.programa = programa;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
