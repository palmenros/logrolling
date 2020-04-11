package com.logrolling.client;

import android.graphics.Color;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderDatos> {

    ArrayList<Pair> dataList;

    public DataAdapter(ArrayList<Pair> listaDatos) {
        this.dataList = listaDatos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato=(TextView)itemView.findViewById(R.id.IdDato);
        }

        public void dataAssignment(Pair pair) {
            dato.setTextSize(20);
            dato.setWidth(1500);
            dato.setHeight(700);
            dato.setText((CharSequence) pair.first);
            int azul= Color.parseColor("#2699FB");
            int blanco= Color.parseColor("#FFFFFF");
            if((Boolean) pair.second){
                dato.setBackgroundColor(azul);
                dato.setTextColor(blanco);
                dato.setGravity(Gravity.RIGHT);
            }else{
                dato.setBackgroundColor(blanco);
                dato.setTextColor(azul);
                dato.setGravity(Gravity.LEFT);
            }
        }
    }
    public DataAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolderDatos holder, int i) {
        holder.dataAssignment(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}