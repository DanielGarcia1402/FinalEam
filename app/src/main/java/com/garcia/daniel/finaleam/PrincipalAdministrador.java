package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrincipalAdministrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_administrador);
    }

    public void verEstudiantes(View view) {
        Intent intent = new Intent(PrincipalAdministrador.this, VerUsuarios.class);
        startActivity(intent);
    }

    public void gestionarTV(View view) {
    }

    public void gestionarConsolas(View view) {
    }

    public void gestionarHorariosReservas(View view) {
    }

    public void reglamento(View view) {
    }

    public void verHorariosReservas(View view) {
    }

    public void salir(View view) {
        Intent intent = new Intent(PrincipalAdministrador.this, MainActivity.class);
        startActivity(intent);
    }
}
