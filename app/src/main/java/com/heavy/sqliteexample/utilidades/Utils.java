package com.heavy.sqliteexample.utilidades;

public class Utils {

    public static final String TABLA_USUARIO = "usuario";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +TABLA_USUARIO+" ("+ID+" INTEGER, "+NOMBRE+" TEXT, "+TELEFONO+" TEXT)";

    public static final String TABLA_MASCOTA = "mascota";
    public static final String ID_MASCOTA = "id_mascota";
    public static final String NOMBRE_MASCOTA = "nombre_mascota";
    public static final String RAZA_MASCOTA = "raza_mascota";
    public static final String ID_DUENIO = "id_duenio";

    public static final String CREAR_TABLA_MASCOTA =
            "CREATE TABLE " +TABLA_MASCOTA+" ("+ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NOMBRE_MASCOTA+" TEXT, "+RAZA_MASCOTA+" TEXT, "+ID_DUENIO+" INTEGER)";


}
