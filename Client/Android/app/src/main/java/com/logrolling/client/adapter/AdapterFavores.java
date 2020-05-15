package com.logrolling.client.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.logrolling.client.R;
import com.logrolling.client.transfer.TransferFavor;

import java.util.ArrayList;

public class AdapterFavores extends RecyclerView.Adapter<AdapterFavores.ViewHolderFavor> {
    ArrayList<TransferFavor> listaFavores;

    public AdapterFavores(ArrayList<TransferFavor> listaFavores) {
        this.listaFavores = listaFavores;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewHolderFavor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favores, parent, false);
        return new AdapterFavores.ViewHolderFavor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavor holder, int position) {
        holder.name.setText("Por: " + listaFavores.get(position).getCreator());
        holder.distance.setText(listaFavores.get(position).getDistance());
        holder.maxTime.setText(listaFavores.get(position).getFormattedDueTime());
        //holder.photo.setImageResource(listaFavores.get(position).getPhoto());
        holder.adress.setText(listaFavores.get(position).getAddress());
        holder.favor.setText(listaFavores.get(position).getTitle());
        holder.price.setText(Integer.toString(listaFavores.get(position).getReward()));
    }

    @Override
    public int getItemCount() {
        return listaFavores.size();
    }

    public class ViewHolderFavor extends RecyclerView.ViewHolder {
        TextView name, maxTime, distance, adress, favor, price;
        ImageView photo;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolderFavor(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            maxTime = (TextView) itemView.findViewById(R.id.Tiempo);
            distance = (TextView) itemView.findViewById(R.id.Distancia);
            photo = (ImageView) itemView.findViewById(R.id.Photo);
            adress = (TextView) itemView.findViewById(R.id.Adress);
            favor = (TextView) itemView.findViewById(R.id.Favor);
            price = (TextView) itemView.findViewById(R.id.Price);

            photo.setClipToOutline(true);
        }
    }
}
