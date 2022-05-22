 package com.example.parcialdos_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialdos_movil.db.DbEmpleados;

 public class RegistrarActivity extends AppCompatActivity {

    EditText txtNombre, txtSalario, txtBase;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNombre = findViewById(R.id.txtNombre);
        txtSalario = findViewById(R.id.txtSalario);
        txtBase = findViewById(R.id.txtBase);
        btnGuardar = findViewById(R.id.btnGuarda);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se inicializa la clase de la base dedatos
                DbEmpleados dbEmpleados = new DbEmpleados(RegistrarActivity.this);
                //Se le asignan los valores de entrada a la función de insertar empleado.
                long id = dbEmpleados.insertarEmpleado(txtNombre.getText().toString(), txtSalario.getText().toString(), txtBase.getText().toString());
                //Se hace una comprobación para que indique si entró correctamente el registro a la base de datos
                if (id > 0){
                    Toast.makeText(RegistrarActivity.this, "Se registró el empleado de forma correcta.", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(RegistrarActivity.this, "Error al registrar el empleado. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //Limpia los campos de entrada de la aplicación para insertar un nuevo empleado cuando se registra correctamente un empleado en la base de datos
    private void limpiarCampos() {
        txtNombre.setText("");
        txtSalario.setText("");
        txtBase.setText("");
    }
}