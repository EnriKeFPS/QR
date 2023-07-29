package com.enrike.lectorcarnet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
    public List<Persona> personas;

    public AdapterList(List<Persona> Mostrarpersonas)
    {
        this.personas=Mostrarpersonas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tid.setText("ID = "+personas.get(position).getId());
        holder.tname.setText("NOMBRE = "+personas.get(position).getNombre());
        holder.tcarrer.setText("CARRERA = "+personas.get(position).getCarrera());
        holder.tentry.setText("ENTRADA = "+personas.get(position).getEntrada());
        holder.tout.setText("SALIDA = "+personas.get(position).getSalida());
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tid,tname,tcarrer,tentry, tout;

        public ViewHolder(View itemView) {
            super(itemView);
            tid = itemView.findViewById(R.id.txtid);
            tname = itemView.findViewById(R.id.txtnombre);
            tcarrer = itemView.findViewById(R.id.txtcarrera);
            tentry = itemView.findViewById(R.id.txtentrada);
            tout = itemView.findViewById(R.id.txtsalida);
        }
    }
}
