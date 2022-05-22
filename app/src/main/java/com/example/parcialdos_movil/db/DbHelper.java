package com.example.parcialdos_movil.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Hay que heredar la clase SQLite
public class DbHelper extends SQLiteOpenHelper {
    //Se utiliza para crear los elementos de bases de datos y tablas

    //Se debe cambiar el númer ocada vez que se hagan modificaciones a la estructura de las tablas o caundo se agreguen nuevas tablas
    private static final int DATABASE_VERSION = 1;
    //Contiene el nombre que se le dará a base de datos. Se puede buscar en el Device File Manager, en la carpeta data y en la carpeta de la aplicación
    private static final String DATABASE_NOMBRE = "empleados.db";
    //Contiene el nombre de la tabla que se va a crear
    public static final String TABLE_EMPLEADOS = "t_empleados";

    //Constructor
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se ejecuta cuando se crea la base de datos
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "salario TEXT NOT NULL," +
                "base TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Se ejecuta cuando se hacen cabios a la base de datos y se cambia la variable de versión de base de datos
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_EMPLEADOS);
        onCreate(sqLiteDatabase);
    }
}
