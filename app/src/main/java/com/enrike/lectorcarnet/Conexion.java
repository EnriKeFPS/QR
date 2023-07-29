package com.enrike.lectorcarnet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    static String DBNAME ="AppFinal";
    static String persona = "Personas";
    static int DBVERSION = 1;

    String sqlpersonas ="CREATE TABLE "+persona+"(id TEXT, nombre TEXT, carrera TEXT, entrada TEXT, salida TEXT);";
    public Conexion(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlpersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table If exists " + persona);
        onCreate(db);
    }

    public void agregar(String i, String n, String c, String e, String s) {
        SQLiteDatabase db;
        db=getWritableDatabase();

        String info = "INSERT INTO "+persona+" Values('"+i+"','"+n+"','"+c+"','"+e+"', '"+s+"')";

        if (db!=null) {
            db.execSQL(info);
            db.close();
        }
    }

    public void agregarsalida(String i, String n, String c, String e, String s) {
        SQLiteDatabase db;
        db=getWritableDatabase();

        String info = "INSERT INTO "+persona+" Values('"+i+"'','"+n+"','"+c+"','"+e+"','"+s+"')";

        if (db!=null) {
            db.execSQL(info);
            db.close();
        }
    }

    public void obtenerpersona(Persona person, String i) {
        SQLiteDatabase db;
        db=getReadableDatabase();

        Cursor datos = db.rawQuery("SELECT * FROM "+persona+" WHERE id='"+i+"'",null);

        if (datos.moveToFirst()){
            do{
                person.setId(datos.getString(0));
                person.setNombre(datos.getString(1));
                person.setCarrera(datos.getString(2));
                person.setEntrada(datos.getString(3));
                person.setSalida(datos.getString(4));
            }while (datos.moveToNext());
        }
    }

    public void borrarpersona(String i, String s) {
        SQLiteDatabase db;
        db=getWritableDatabase();

        String info = "DELETE FROM "+persona+" WHERE id='"+i+"' and salida='"+s+"'";

        if (db!=null) {
            db.execSQL(info);
            db.close();
        }
    }


    public List<Persona> GetAll_register() {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor datos = bd.rawQuery("SELECT * FROM Personas",null);
        List<Persona> personas = new ArrayList<>();

        if (datos.moveToFirst()) {
            do{
                personas.add(new Persona(
                        datos.getString(0),
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4)
                ));
            }while (datos.moveToNext());
        }

        return personas;

    }

}
