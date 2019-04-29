package com.garcia.daniel.finaleam.Controlador;

import android.app.Activity;

import com.garcia.daniel.finaleam.DAO.FacultadDAO;
import com.garcia.daniel.finaleam.Modelo.Facultad;

import java.util.List;

public class CtlFacultad {
    FacultadDAO dao;

    public CtlFacultad(Activity activity) {
        dao = new FacultadDAO(activity);
    }


    public List<Facultad> listarFacultad() {
        return dao.Listar();
    }
}
