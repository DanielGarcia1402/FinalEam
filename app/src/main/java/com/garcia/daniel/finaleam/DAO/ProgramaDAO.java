package com.garcia.daniel.finaleam.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.garcia.daniel.finaleam.Infraestructura.Conexion;
import com.garcia.daniel.finaleam.Modelo.Programa;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ProgramaDAO {
    Conexion conexion;

    public ProgramaDAO(Activity activity){
        //Creacion o conexion de la base d edatos en caso que no exista
        //ademas se indica el numero de version d ela base de datos
        conexion = new Conexion(activity);
    }


    public List<Programa> Listar() {
        List<Programa> listaProgramas = new ArrayList<Programa>();
        String consulta = "select nombre,codigo from programa";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.moveToFirst()) {
            do {
                Programa programa = new Programa(

                        temp.getString(0),
                        Integer.parseInt(temp.getString(1))
                );

                listaProgramas.add(programa);
            } while (temp.moveToNext());
        }
        return listaProgramas;
    }
}
