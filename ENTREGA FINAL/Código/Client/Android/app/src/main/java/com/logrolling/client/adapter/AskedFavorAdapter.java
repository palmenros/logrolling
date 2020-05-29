package com.logrolling.client.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.view.ClickListener;
import com.logrolling.client.web.WebRequestQueue;

import java.util.ArrayList;

public class AskedFavorAdapter extends RecyclerView.Adapter<AskedFavorAdapter.FavorViewHolder> {
    ArrayList<TransferFavor> favorList;
    private ClickListener clickListener;

    public AskedFavorAdapter(ArrayList<TransferFavor> favorList, ClickListener clickListener) {
        this.favorList = favorList;
        this.clickListener = clickListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favor, parent, false);
        view.setOnClickListener((clickView) -> {
            clickListener.onClick(clickView);
        });
        return new FavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorViewHolder holder, int position) {
        String worker = favorList.get(position).getWorker();
        if (worker != null) {
            holder.name.setText("Asignado a: " + worker);
        } else {
            holder.name.setText("No asignado");
        }

        holder.distance.setText(favorList.get(position).getDistance());
        holder.maxTime.setText(favorList.get(position).getFormattedDueTime());
        holder.photo.setImageUrl(
                Controller.getInstance().getFavorImageURL(favorList.get(position).getId()),
                WebRequestQueue.getInstance().getImageLoader()
        );
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
        NetworkImageView photo;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public FavorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            maxTime = (TextView) itemView.findViewById(R.id.Tiempo);
            distance = (TextView) itemView.findViewById(R.id.Distancia);
            photo = (NetworkImageView) itemView.findViewById(R.id.Photo);
            adress = (TextView) itemView.findViewById(R.id.Adress);
            favor = (TextView) itemView.findViewById(R.id.Favor);
            price = (TextView) itemView.findViewById(R.id.Price);

            photo.setClipToOutline(true);
        }
    }
}
