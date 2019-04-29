package com.garcia.daniel.finaleam.Controlador;

import android.app.Activity;

import com.garcia.daniel.finaleam.DAO.UsuarioDAO;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.List;

public class CtlUsuario {
    UsuarioDAO dao;

    public CtlUsuario(Activity activity) {
        dao = new UsuarioDAO(activity);
    }

    public boolean guardarUsuario(String nombres,String apellidos,int cedula,int codigoEstudiante,
                                  String facultad,String programa,String fechaNacimiento,
                                  String correo,String password,int estado,String rol){
        Usuario usuario = new Usuario(
                nombres,apellidos,cedula,codigoEstudiante,facultad,programa,fechaNacimiento,correo,password,estado,rol
        );
        return dao.guardar(usuario);
    }

    public Usuario buscarUsuario(int cedula) {
        return dao.buscar(cedula);
    }

    public boolean eliminarUsuario(int cedula) {
        Usuario usuario = new Usuario("", "",cedula, 0,"", "", "", "", "", 0, "");
        return dao.eliminar(usuario);
    }

    public boolean modificarUsuario(String nombres,String apellidos,int cedula,int codigoEstudiante,
                                    String facultad,String programa,String fechaNacimiento,
                                    String correo,String password,int estado,String rol){
        Usuario usuario = new Usuario(
                nombres,apellidos,cedula,codigoEstudiante,facultad,programa,fechaNacimiento,correo,password,estado,rol
        );
        return dao.modificar(usuario);
    }

    public List<Usuario> listarUsuario() {
        return dao.Listar();
    }
}
