package com.logrolling.client.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logrolling.client.R;
import com.logrolling.client.transfer.Gift;

import java.util.ArrayList;


public class AdapterRegalos extends RecyclerView.Adapter<AdapterRegalos.ViewHolderRegalos> {

    ArrayList<Gift> listaRegalos;

    public AdapterRegalos(ArrayList<Gift> listaRegalos) {
        this.listaRegalos = listaRegalos;
    }


    @NonNull
    @Override
    public AdapterRegalos.ViewHolderRegalos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_regalos,parent,false);
        return new AdapterRegalos.ViewHolderRegalos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRegalos.ViewHolderRegalos holder, int position) {
        holder.name.setText(listaRegalos.get(position).getName());
        holder.price.setText(Integer.toString(listaRegalos.get(position).getPrice()));
        //holder.photo.setImageResource(listaRegalos.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return listaRegalos.size();
    }

    public class ViewHolderRegalos extends RecyclerView.ViewHolder {
        public TextView name,price;
        public ImageView photo;

        public ViewHolderRegalos(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.Name);
            price=(TextView)itemView.findViewById(R.id.price);
            photo=(ImageView)itemView.findViewById(R.id.Photo);

            photo.setClipToOutline(true);
        }
    }
}