package com.example.parcialdos_movil.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialdos_movil.R;
import com.example.parcialdos_movil.VerActivity;
import com.example.parcialdos_movil.entidades.Empleados;

import java.util.ArrayList;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {


    ArrayList<Empleados> listaEmpleados;

    //Constructor que asigna a la lista del contexto la información que le entra a la función
    public ListaEmpleadosAdapter(ArrayList<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    //Adaptador ayuda a generar y asignar los elementos que van a ir en la RecyclerView y reciclará la vista para llenar con todos los registros
    @NonNull
    @Override
    //Asigna cuál va a ser el diseño de cada uno de los elementos de la lista
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Asigna el elemento de lista para mostrar los empleados en la vista principal
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
        //Se envia la infomración a la vista
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        //Asigna los elementos individuales de la tabla a los elementos individuales de la vista
        holder.viewNombre.setText("Nombre: "+listaEmpleados.get(position).getNombre());
        holder.viewSalario.setText("Salario: "+listaEmpleados.get(position).getSalario());
        holder.viewBase.setText("Base Prestacional: "+listaEmpleados.get(position).getBase());
    }

    @Override
    public int getItemCount() {
        //Muestra el tamaño de la lista
        return listaEmpleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewSalario, viewBase;


        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            //Se asigna cada uno de los elementos declarados
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewSalario = itemView.findViewById(R.id.viewSalario);
            viewBase = itemView.findViewById(R.id.viewBase);

            //Función para ver los registros individuales
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaEmpleados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
