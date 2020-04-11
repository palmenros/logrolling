package com.logrolling.client;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<Pair> listaDatos;

    public AdapterDatos(ArrayList<Pair> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato=(TextView)itemView.findViewById(R.id.IdDato);
        }

        public void asignarDatos(Pair pair) {
            //dato.setTextSize(20);
            //dato.setWidth(1500);
            //dato.setHeight(700);
            dato.setText((CharSequence) pair.first);
            int azul= Color.parseColor("#2699FB");
            int blanco= Color.parseColor("#FFFFFF");

            if((Boolean) pair.second){
                dato.setBackgroundResource(R.drawable.this_user_message);
                //dato.setBackgroundColor(azul);
                dato.setTextColor(blanco);
                //dato.setGravity(Gravity.RIGHT);
            }else{
                dato.setBackgroundResource(R.drawable.other_user_message);
            }
        }
    }
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int i) {
        holder.asignarDatos(listaDatos.get(i));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }
}