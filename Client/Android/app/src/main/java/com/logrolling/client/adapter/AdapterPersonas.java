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
import com.logrolling.client.transfer.TransferMessagePreview;

import java.util.ArrayList;

public class AdapterPersonas extends RecyclerView.Adapter<AdapterPersonas.ViewHolderPersonas> {

    ArrayList<TransferMessagePreview> listaTransferMessagePreviews;

    public AdapterPersonas(ArrayList<TransferMessagePreview> listaTransferMessagePreviews) {
        this.listaTransferMessagePreviews = listaTransferMessagePreviews;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewHolderPersonas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_personas, parent, false);
        return new AdapterPersonas.ViewHolderPersonas(view);
    }

    @Override
    public int getItemCount() {
        return listaTransferMessagePreviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersonas holder, int position) {
        holder.name.setText(listaTransferMessagePreviews.get(position).getUser());
        holder.last_message.setText(listaTransferMessagePreviews.get(position).getMessage());
        // holder.photo.setImageResource(listaPersonas.get(position).getPhoto());
    }

    public class ViewHolderPersonas extends RecyclerView.ViewHolder {
        TextView name, last_message;
        ImageView photo;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolderPersonas(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            last_message = (TextView) itemView.findViewById(R.id.last_message);
            photo = (ImageView) itemView.findViewById(R.id.Photo);
            photo.setClipToOutline(true);
        }
    }
}
