package com.garcia.daniel.finaleam.Infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    private static final String database ="finalAndroid.db";
    //Manipular regitro a la base de datos
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;

    SQLiteDatabase db;

    //Conexion si se requiere especificar otro db
    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //Para usar la base de datos establecida
    public Conexion(Context context) {
        super(context, database, factory, version);
    }

    public void cerrarConexion() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table usuario(" +
                "nombres text, " +
                "apellidos text, " +
                "cedula integer primary key, " +
                "codigoEstudiante integer, " +
                "facultad text , " +
                "programa text , " +
                "fechaNacimiento text , " +
                "correo text , " +
                "password text," +
                "estado integer," +
                "rol text" +
                ")");

        bd.execSQL("insert into usuario values('Daniel Felipe', 'Garcia', 1010028535, " +
                "240220191002, 'Ingenieria', 'Software', '26/04/2019'," +
                "'danielfelipeg2000@gmail.com', '1234', 1, 'Administrador')");


        bd.execSQL("create table programa(" +
                "nombre text, " +
                "codigo integer primary key " +
                ")");

        bd.execSQL("insert into programa values('Software', 212123)");
        bd.execSQL("insert into programa values('Industrial', 104352)");
        bd.execSQL("insert into programa values('Mecatronica', 091190)");
        bd.execSQL("insert into programa values('Administracion de empresas', 897123)");


        bd.execSQL("create table facultad(" +
                "nombre text, " +
                "codigo integer primary key, " +
                "codigoPrograma integer REFERENCES programa ON DELETE CASCADE " +
                ")");

        bd.execSQL("insert into facultad values('Ingenieria', 1234, 212123)");
        bd.execSQL("insert into facultad values('Ingenieria', 0987, 091190)");
        bd.execSQL("insert into facultad values('Contabilidad', 4567, 897123)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNueva) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists programa");
        db.execSQL("drop table if exists facultad");
        onCreate(db);
    }

    public boolean ejecutarInsert(String tabla, ContentValues registro){
        try {
            //Objeto para lectura y escritura en la base de datos
            db = this.getWritableDatabase();
            //Null es los campos que no se van a registrar, y retorna -1 si hubo error
            int res = (int) db.insert(tabla, null, registro);
            cerrarConexion();
            if (res != -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ejecutarDelete(String tabla, String condicion) {
        db = this.getWritableDatabase();
        //SI la clausula where esta con ?, en este otro parametro se envian los datos
        //Ejemplo
        //db.delete("tablename", )
        int cant = db.delete(tabla, condicion, null);
        cerrarConexion();

        if(cant >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ejecutarUpdate(String tabla, String condicion, ContentValues registro) {
        try {
            db = this.getWritableDatabase();
            int cant = db.update(tabla, registro, condicion, null);
            cerrarConexion();

            if(cant == 1){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Cursor ejecutarSearch(String consulta) {
        try {
            //Objeto para lectura y escritura de la base de datos
            db = this.getWritableDatabase();
            //Almacnamos en un objeto la info de la bd, ademas ejecutamos una consulta sql.
            //E el null se especifican los parametros, dado el que en el sql no
            Cursor fila = db.rawQuery(consulta, null);
            return fila;
        } catch (Exception e) {
            cerrarConexion();
            return null;
        }
    }

}
