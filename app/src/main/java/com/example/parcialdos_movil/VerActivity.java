package com.example.parcialdos_movil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parcialdos_movil.db.DbEmpleados;
import com.example.parcialdos_movil.entidades.Empleados;

public class VerActivity extends AppCompatActivity {

    TextView viewNombreInd, viewSalarioInd, viewBaseInd, viewPrima, viewCesantias, viewVacaciones, viewSalud, viewPension, viewCompensacion, viewNominaTotal;
    Button btnEliminar;

    Empleados empleado;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        //Atributos que ingresa el empleado
        viewNombreInd = findViewById(R.id.viewNombreIndividual);
        viewSalarioInd = findViewById(R.id.viewSalarioIndividual);
        viewBaseInd = findViewById(R.id.viewBaseIndividual);
        //Atributos de prestaciones que se calculan
        viewPrima = findViewById(R.id.viewPrima);
        viewCesantias = findViewById(R.id.viewCesantias);
        viewVacaciones = findViewById(R.id.viewVacaciones);
        viewSalud = findViewById(R.id.viewSalud);
        viewPension = findViewById(R.id.viewPension);
        viewCompensacion = findViewById(R.id.viewCompensacion);
        viewNominaTotal = findViewById(R.id.viewNominaTotal);
        //Botón para eliminar el registro
        btnEliminar = findViewById(R.id.btnEliminar);

        //Validación de la variable que viene de la vista principal
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbEmpleados dbEmpleados = new DbEmpleados(VerActivity.this);
        empleado = dbEmpleados.verEmpleado(id);

        if (empleado != null){
            //Pasar la info del empleado a la vista
            viewNombreInd.setText("Nombre: "+empleado.getNombre());
            viewSalarioInd.setText("Salario: "+empleado.getSalario());
            viewBaseInd.setText("Base Prestacional: "+empleado.getBase());

            int salarioConvertido = Integer.parseInt(empleado.getSalario());

            //Calcula y pasar las prestaciones a la vista
            viewPrima.setText("Prima: " + ((salarioConvertido*31)/360));
            viewCesantias.setText("Cesantías: " + ((salarioConvertido*31) / 360));
            viewVacaciones.setText("Vacaciones: " + ((salarioConvertido*31) / 720));
            viewSalud.setText("Salud: " + (salarioConvertido*0.125));
            viewPension.setText("Pensión: " + (salarioConvertido*0.16));
            viewCompensacion.setText("Compensación: " + (salarioConvertido*0.04));
            viewNominaTotal.setText("Total Valor Nómina: " + (salarioConvertido + ((salarioConvertido*31)/360) + ((salarioConvertido*31) / 360) + ((salarioConvertido*31) / 720) + (salarioConvertido*0.125) + (salarioConvertido*0.16) + (salarioConvertido*0.04)));
        }

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //El Builder se utiliza en este caso para generar un mensaje de alerta con los botones de sí y no para confirmar la eliminación
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                //Mensaje de alerta y los eventos onClick para la respuesta positiva y negativa
                builder.setMessage("¿Desea eliminar el registro del empleado?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbEmpleados.eliminarEmpleado(id)) {
                            lista();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });


    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}