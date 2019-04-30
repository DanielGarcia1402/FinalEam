package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.garcia.daniel.finaleam.Controlador.CtlUsuario;
import com.garcia.daniel.finaleam.Infraestructura.Conexion;
import com.garcia.daniel.finaleam.Modelo.Usuario;

public class MainActivity extends AppCompatActivity {
    EditText txtUsu, txtPass;

    CtlUsuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsu = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);

        usuario = new CtlUsuario(this);
    }

    public void iniciarSesion(View view) {
        String usua = txtUsu.getText().toString();
        String pass = txtPass.getText().toString();

        if(!usua.isEmpty()){
            if(!pass.isEmpty()){
                int cedula = Integer.parseInt(usua);
                Usuario usuariooo = usuario.buscarUsuario(cedula);

                if(usuariooo != null){
                    if(cedula == usuariooo.getCedula() && pass.equals(usuariooo.getPassword())){
                        if(usuariooo.getEstado() == 1){

                            /*Toast.makeText(this, "Paso: "+usuariooo.getCedula()+
                                " -pass "+usuariooo.getPassword() + " -rol "+usuariooo.getRol()+
                                "-estado "+usuariooo.getEstado() , Toast.LENGTH_SHORT).show();*/

                            String roule = usuariooo.getRol();
                            String admin = "Administrador";
                            String estu = "Estudiante";

                            if(roule.equals(admin)){
                                Toast.makeText(this, "Bienvenido Admin "+usuariooo.getNombres(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, PrincipalAdministrador.class);
                                startActivity(intent);
                                finish();
                            }else {
                                if(roule.equals(estu)){
                                    Toast.makeText(this, "Bienvenido Estudiante "+usuariooo.getNombres(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, PrincipalEstudiante.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }


                        } else {
                            Toast.makeText(this, "El usuario esta inactivo", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Usuario o password incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                    limpiar();
                }

            } else {
                Toast.makeText(this, "El campo password no debe estar vacio", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El campo usuario esta vacio no debe estar vacio", Toast.LENGTH_SHORT).show();
        }

    }

    public void irRegistro(View view) {
        Intent intent = new Intent(MainActivity.this, RegistroUsuario.class);
        startActivity(intent);
    }

    public void verBD(View view) {
        Toast.makeText(this, ""+ DebugDB.getAddressLog(), Toast.LENGTH_LONG).show();
    }

    public void limpiar() {
        txtUsu.setText("");
        txtPass.setText("");
    }
}
