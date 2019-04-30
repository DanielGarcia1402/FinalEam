package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.garcia.daniel.finaleam.Controlador.CtlUsuario;

public class ActivarUsuarios extends AppCompatActivity {

    TextView nombre, cedula, rol;
    String TxtNombre, TxtRol;
    int TxtEstado, TxtCedula;

    CtlUsuario controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_usuarios);

        nombre = findViewById(R.id.txtNombreActi);
        cedula = findViewById(R.id.txtCedulaActi);
        rol = findViewById(R.id.txtRolActi);

        Intent intent = getIntent();

        TxtNombre = intent.getStringExtra("nombre");
        TxtCedula = intent.getIntExtra("cedula", TxtCedula);
        //TxtCedula = intent.getStringExtra("cedula");
        TxtRol = intent.getStringExtra("rol");

        nombre.setText("Nombre: "+TxtNombre);
        cedula.setText("Cedula: "+TxtCedula);
        rol.setText("Rol: "+TxtRol);

        controlador = new CtlUsuario(this);

    }

    public void activarUsuario(View view) {
        if(controlador.modificarUsuario(TxtCedula)){
            Toast.makeText(this, "Se modifico el usuario", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ActivarUsuarios.this, VerUsuarios.class);
            startActivity(intent);

        } else{
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_SHORT).show();
        }
    }
}
