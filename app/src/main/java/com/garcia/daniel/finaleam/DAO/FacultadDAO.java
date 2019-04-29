package com.garcia.daniel.finaleam.DAO;

import android.app.Activity;
import android.database.Cursor;

import com.garcia.daniel.finaleam.Infraestructura.Conexion;
import com.garcia.daniel.finaleam.Modelo.Facultad;
import com.garcia.daniel.finaleam.Modelo.Programa;

import java.util.ArrayList;
import java.util.List;

public class FacultadDAO {
    Conexion conexion;

    public FacultadDAO(Activity activity){
        //Creacion o conexion de la base d edatos en caso que no exista
        //ademas se indica el numero de version d ela base de datos
        conexion = new Conexion(activity);
    }


    public List<Facultad> Listar() {
        List<Facultad> listaFacultades = new ArrayList<Facultad>();
        String consulta = "select nombre,codigo,codigoPrograma from facultad";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.moveToFirst()) {
            do {
                Facultad facultad = new Facultad(

                        temp.getString(0),
                        Integer.parseInt(temp.getString(1)),
                        Integer.parseInt(temp.getString(2))
                );

                listaFacultades.add(facultad);
            } while (temp.moveToNext());
        }
        return listaFacultades;
    }
}
