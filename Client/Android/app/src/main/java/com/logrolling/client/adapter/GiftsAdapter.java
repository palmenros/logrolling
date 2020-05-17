package com.logrolling.client.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logrolling.client.R;
import com.logrolling.client.transfer.TransferGift;
import com.logrolling.client.view.ClickListener;

import java.util.ArrayList;


public class GiftsAdapter extends RecyclerView.Adapter<GiftsAdapter.GiftViewHolder> {

    private ArrayList<TransferGift> giftList;
    private ClickListener clickListener;

    public GiftsAdapter(ArrayList<TransferGift> giftList, ClickListener clickListener ) {
        this.giftList = giftList;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        view.setOnClickListener((clickView) -> {
            clickListener.onClick(clickView);
        });
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftViewHolder holder, int position) {
        holder.name.setText(giftList.get(position).getTitle());
        holder.price.setText(Integer.toString(giftList.get(position).getPrice()));
        //holder.photo.setImageResource(listaRegalos.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public class GiftViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public ImageView photo;

        public GiftViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            price = (TextView) itemView.findViewById(R.id.price);
            photo = (ImageView) itemView.findViewById(R.id.Photo);

            photo.setClipToOutline(true);
        }
    }
}