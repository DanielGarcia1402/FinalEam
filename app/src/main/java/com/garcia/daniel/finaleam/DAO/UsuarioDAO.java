package com.garcia.daniel.finaleam.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.garcia.daniel.finaleam.Infraestructura.Conexion;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Conexion conexion;

    public UsuarioDAO(Activity activity){
        //Creacion o conexion de la base d edatos en caso que no exista
        //ademas se indica el numero de version d ela base de datos
        conexion = new Conexion(activity);
    }

    public boolean guardar(Usuario usuario) {
        //Objeto que contendra la info a almacenar
        ContentValues registro = new ContentValues();
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("cedula", usuario.getCedula());
        registro.put("codigoEstudiante", usuario.getCodigoEstudiante());
        registro.put("facultad", usuario.getFacultad());
        registro.put("programa", usuario.getPrograma());
        registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        registro.put("correo", usuario.getCorreo());
        registro.put("password", usuario.getPassword());
        registro.put("estado", usuario.getEstado());
        registro.put("rol", usuario.getRol());
        return conexion.ejecutarInsert("usuario", registro);
    }

    public Usuario buscar(int cedula) {
        Usuario usuario = null;
        String consulta = "select nombres,apellidos,cedula,codigoEstudiante,facultad,programa," +
                "fechaNacimiento,correo,password,estado,rol from usuario where cedula = "+"'"+cedula+"'";
        Cursor temp = conexion.ejecutarSearch(consulta);

        //Encontro algun registro
        if(temp.getCount()>0) {
            temp.moveToFirst();
            usuario = new Usuario(
                    temp.getString(0), temp.getString(1), cedula, temp.getInt(3),
                    temp.getString(4), temp.getString(5), temp.getString(6), temp.getString(7),
                    temp.getString(8), Integer.parseInt(temp.getString(9)), temp.getString(10));
            Log.i("===============DATO 0", temp.getString(0));
            Log.i("===============DATO 1", temp.getString(1));
            Log.i("===============DATO 2", temp.getString(2));
            Log.i("===============DATO 3", temp.getString(3));
            Log.i("===============DATO 4", temp.getString(4));
            Log.i("===============DATO 5", temp.getString(5));
            Log.i("===============DATO 6", temp.getString(6));
            Log.i("===============DATO 7", temp.getString(7));
            Log.i("===============DATO 8", temp.getString(8));
            Log.i("===============DATO 9", temp.getString(9));
            Log.i("===============DATO 10", temp.getString(10));
        }

        conexion.cerrarConexion();
        return usuario;
    }

    public boolean eliminar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "cedula=" + usuario.getCedula();
        return conexion.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "cedula=" + usuario.getCedula();
        int estado = 1;

        ContentValues registro = new ContentValues();

        //registro.put("nombres", usuario.getNombres());
        //registro.put("apellidos", usuario.getApellidos());
        //registro.put("cedula", usuario.getCedula());
        //registro.put("codigoEstudiante", usuario.getCodigoEstudiante());
        //registro.put("facultad", usuario.getFacultad());
        //registro.put("programa", usuario.getPrograma());
        //registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        //registro.put("correo", usuario.getCorreo());
        //registro.put("password", usuario.getPassword());
        registro.put("estado", estado);
        //registro.put("rol", usuario.getRol());

        return conexion.ejecutarUpdate(tabla, condicion, registro);
    }

    public List<Usuario> Listar(int estado) {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String consulta = "select nombres,apellidos,cedula,codigoEstudiante,facultad,programa," +
                "fechaNacimiento,correo,password,estado,rol from usuario where rol = 'Estudiante' and estado="+estado;
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(

                        temp.getString(0),
                        temp.getString(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getString(7),
                        temp.getString(8),
                        temp.getInt(9),
                        temp.getString(10));

                listaUsuarios.add(usuario);
            } while (temp.moveToNext());
        }
        return listaUsuarios;
    }
}
