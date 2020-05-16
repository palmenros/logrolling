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

public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.FavorViewHolder> {
    ArrayList<TransferFavor> favorList;

    public FavorAdapter(ArrayList<TransferFavor> favorList) {
        this.favorList = favorList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favores, parent, false);
        return new FavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorViewHolder holder, int position) {
        holder.name.setText("Por: " + favorList.get(position).getCreator());
        holder.distance.setText(favorList.get(position).getDistance());
        holder.maxTime.setText(favorList.get(position).getFormattedDueTime());
        //holder.photo.setImageResource(listaFavores.get(position).getPhoto());
        holder.adress.setText(favorList.get(position).getAddress());
        holder.favor.setText(favorList.get(position).getTitle());
        holder.price.setText(Integer.toString(favorList.get(position).getReward()));
    }

    @Override
    public int getItemCount() {
        return favorList.size();
    }

    public class FavorViewHolder extends RecyclerView.ViewHolder {
        TextView name, maxTime, distance, adress, favor, price;
        ImageView photo;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public FavorViewHolder(@NonNull View itemView) {
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