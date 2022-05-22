package com.example.parcialdos_movil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parcialdos_movil.entidades.Empleados;

import java.util.ArrayList;

//Hereda la DbHelper para poder manipular la base de datos
public class DbEmpleados extends DbHelper{
    //Ayuda a realizar las transacciones a la tabla de la base de datos
    Context context;

    public DbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //Recibe parámetros para llenar la tabla
    public long insertarEmpleado(String nombre, String salario, String base){

        long id = 0;

        //try catch para corroborar que esté correctto antes de entrar a la base de datos
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //Se utiliza para insertar cada una de las columnas, primer parametro: nombre de columna, segundo parámetro: variable de entrada
            values.put("nombre", nombre);
            values.put("salario", salario);
            values.put("base", base);

            //se asigna a id por que es autincrementable, le entra el nombre de latabla, null y los valores a ingresar
            id = db.insert(TABLE_EMPLEADOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        //retorna el id
        return id;
    }

    //Retorna la lista de empleados almacenados en la base de datos para mostrarla en la RecyclerView
    public ArrayList<Empleados> mostrarEmpleados(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        Empleados empleado = null;
        Cursor cursorEmpleados = null;

        //Consulta a la tabla de contactos con un cursor, después de TABLE, con más, se puede agregar los filtros de la consulta (WHERE...)
        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS, null);

        //Se mueve el cursor al primer resultado de la consulta
        if(cursorEmpleados.moveToFirst()){
            //Se utiliza la clase de empleados para usar sus mutadores
            do{
                empleado = new Empleados();

                //Trae la información del cursos a partir de la posición de la columna y la inserta en el empleado
                empleado.setId(cursorEmpleados.getInt(0));
                empleado.setNombre(cursorEmpleados.getString(1));
                empleado.setSalario(cursorEmpleados.getString(2));
                empleado.setBase(cursorEmpleados.getString(3));

                //Se agrega el empleado a la lista
                listaEmpleados.add(empleado);

            //Mueve al resultado siguiente
            } while (cursorEmpleados.moveToNext());
        }
        //Se cierra el cursor
        cursorEmpleados.close();

        //Se retorna un Array como se había especificado en el método
        return listaEmpleados;
    }

    //Método para mostrar solo un registro de empleado
    public Empleados verEmpleado(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empleados empleado = null;
        Cursor cursorEmpleados = null;

        //Consulta a la tabla de empleados con un cursor, después de TABLE, con más, se puede agregar los filtros de la consulta (WHERE...)
        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS + " WHERE id = "+ id + " LIMIT 1", null);

        //Se mueve el cursor al primer resultado de la consulta
        if(cursorEmpleados.moveToFirst()){
                //Se utiliza la clase de empleados para usar sus mutadores
                empleado = new Empleados();
                //Trae la información del cursos a partir de la posición de la columna y la inserta en el empleado
                empleado.setId(cursorEmpleados.getInt(0));
                empleado.setNombre(cursorEmpleados.getString(1));
                empleado.setSalario(cursorEmpleados.getString(2));
                empleado.setBase(cursorEmpleados.getString(3));
        }
        //Se cierra el cursor
        cursorEmpleados.close();
        //Se retorna un Array como se había especificado en el método
        return empleado;
    }
    public boolean eliminarEmpleado(int id) {

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Query para eliminar
        try {
            db.execSQL("DELETE FROM " + TABLE_EMPLEADOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;

        } finally {
            db.close();
        }
        return correcto;


    }



}
