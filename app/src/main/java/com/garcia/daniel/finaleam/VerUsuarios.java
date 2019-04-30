package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.garcia.daniel.finaleam.Controlador.CtlUsuario;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class VerUsuarios extends AppCompatActivity {

    CtlUsuario controlador;
    ListView listUsu;
    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios);

        controlador = new CtlUsuario(this);
        listUsu = findViewById(R.id.listViewUsuarios);

        configurarLista();

    }


    private void configurarLista() {
        int esta = 0;
        listaUsuarios = controlador.listarUsuario(esta);
        List<String> listaAdapter = new ArrayList<String>();
        ArrayAdapter<String> adapter;

        if (listaUsuarios.size() > 0) {
            for (int x = 0; x < listaUsuarios.size(); x++) {
                listaAdapter.add(
                        "Nombre: "+listaUsuarios.get(x).getNombres() + " Cedula: "
                                + listaUsuarios.get(x).getCedula() + " Rol: "
                                + listaUsuarios.get(x).getRol());
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
        }

        listUsu.setAdapter(adapter);

        listUsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mostrarInformacion(posicion);
            }
        });
    }

    public void mostrarInformacion(int pos) {
        String nom = listaUsuarios.get(pos).getNombres();
        String rol = listaUsuarios.get(pos).getRol();
        int cedu = listaUsuarios.get(pos).getCedula();
        int esta = listaUsuarios.get(pos).getEstado();

        Toast.makeText(this,
                "Nombre: " + listaUsuarios.get(pos).getNombres()+ " " +
                        "Cedula: " + listaUsuarios.get(pos).getCedula()+ " " +
                        "Rol: " + listaUsuarios.get(pos).getRol()+ " ", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(VerUsuarios.this, ActivarUsuarios.class);
        intent.putExtra("nombre", nom);
        intent.putExtra("cedula", cedu);
        intent.putExtra("rol", rol);
        intent.putExtra("estado", listaUsuarios.get(pos).getEstado());
        startActivity(intent);

    }



    public void Regresar(View view) {
        Intent intent = new Intent(VerUsuarios.this, PrincipalAdministrador.class);
        startActivity(intent);
    }
}
