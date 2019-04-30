package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrincipalEstudiante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_estudiante);
    }

    public void verHorarios(View view) {
    }

    public void verReglamento(View view) {
    }

    public void hacerReservas(View view) {
    }

    public void verReservas(View view) {
    }

    public void irInformacionP(View view) {
    }

    public void salir(View view) {
        Intent intent = new Intent(PrincipalEstudiante.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
