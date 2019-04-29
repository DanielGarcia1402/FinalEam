package com.garcia.daniel.finaleam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.garcia.daniel.finaleam.Controlador.CtlFacultad;
import com.garcia.daniel.finaleam.Controlador.CtlPrograma;
import com.garcia.daniel.finaleam.Controlador.CtlUsuario;
import com.garcia.daniel.finaleam.Modelo.Facultad;
import com.garcia.daniel.finaleam.Modelo.Programa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    EditText nombres,apellidos,cedula,codigoE;
    Spinner facultad, programa;
    EditText fechaNacimiento, correo, password, confirmaPassword;

    CtlUsuario controlador;
    CtlPrograma programaa;
    CtlFacultad facultadd;
    List<Programa> listaProgramas;
    List<Facultad> listaFacultades;
    String programaAlmacena, facultadAlmacena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        nombres = findViewById(R.id.txtNombre);
        apellidos = findViewById(R.id.txtApellidos);
        cedula = findViewById(R.id.txtCedula);
        codigoE = findViewById(R.id.txtCodEstudiante);
        facultad = findViewById(R.id.spnFacultad);
        programa = findViewById(R.id.spnPrograma);
        fechaNacimiento = findViewById(R.id.txtFecha);
        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtPassword);
        confirmaPassword = findViewById(R.id.txtConfirmaPass);

        controlador = new CtlUsuario(this);
        programaa = new CtlPrograma(this);
        facultadd = new CtlFacultad(this);

        configurarLista();
        configurarListaFacultad();


        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo auto generate
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(RegistroUsuario.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedday) {
                        fechaNacimiento.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);

                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Selected Day");
                mDatePicker.show();
            }
        });

    }

    public void registroU(View view) {
        String nom = nombres.getText().toString();
        String ape = apellidos.getText().toString();
        String ced = cedula.getText().toString();
        String cod = codigoE.getText().toString();
        String fecha = fechaNacimiento.getText().toString();
        String corr = correo.getText().toString();
        String pass = password.getText().toString();
        String confpass = confirmaPassword.getText().toString();
        int estado = 0;
        String rol = "Estudiante";
        String facul = "Ingenieria";

        if(!nom.isEmpty()){
            if(!ape.isEmpty()){
                if(!ced.isEmpty()){
                    if(!cod.isEmpty()){
                        if(!fecha.isEmpty()){
                            if(!corr.isEmpty()){
                                if(!pass.isEmpty()){
                                    if(!confpass.isEmpty()){
                                        if(pass.equals(confpass)){
                                            int cedul = Integer.parseInt(ced);
                                            int codi = Integer.parseInt(cod);

                                            if(controlador.guardarUsuario(nom,ape,cedul,codi,facultadAlmacena,programaAlmacena,fecha,corr,pass,estado,rol)){

                                                Toast.makeText(this, "Almacenado correctamente", Toast.LENGTH_SHORT).show();
                                                limpiar();

                                                Intent intent = new Intent(RegistroUsuario.this, MainActivity.class);
                                                startActivity(intent);


                                            }else {
                                                Toast.makeText(this, "Error al almacenar la información :c", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(this, "El campo confirmar contraseña no debe estar vacio", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "El campo password no debe estar vacio", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "El campo correo no debe estar vacio", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "El campo fecha de nacimiento no debe estar vacio", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "El campo cedula no debe estar vacio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "El campo cedula no debe estar vacio", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "El campo apellidos no debe estar vacio", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "El campo nombres no debe estar vacio", Toast.LENGTH_SHORT).show();
        }



    }

    public void regresar(View view) {
        Intent intent = new Intent(RegistroUsuario.this, MainActivity.class);
        startActivity(intent);
    }

    public void limpiar() {
        nombres.setText("");
        apellidos.setText("");
        cedula.setText("");
        codigoE.setText("");
        fechaNacimiento.setText("");
        correo.setText("");
        password.setText("");
        confirmaPassword.setText("");
    }


    private void configurarLista() {
        listaProgramas = programaa.listarPrograma();
        List<String> listaAdapter = new ArrayList<String>();
        ArrayAdapter<String> adapter;

        if (listaProgramas.size() > 0) {
            for (int x = 0; x < listaProgramas.size(); x++) {
                listaAdapter.add(listaProgramas.get(x).getNombre());
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAdapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAdapter);
        }

        programa.setAdapter(adapter);

        programa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                programaAlmacena = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

    }


    private void configurarListaFacultad() {
        listaFacultades = facultadd.listarFacultad();
        List<String> listaAdapter = new ArrayList<String>();
        ArrayAdapter<String> adapter;

        if (listaFacultades.size() > 0) {
            for (int x = 0; x < listaFacultades.size(); x++) {
                listaAdapter.add(listaFacultades.get(x).getNombre());
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAdapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAdapter);
        }

        facultad.setAdapter(adapter);

        facultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                facultadAlmacena = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

    }

}
