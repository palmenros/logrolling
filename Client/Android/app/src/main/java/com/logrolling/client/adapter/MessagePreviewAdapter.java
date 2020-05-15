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

public class MessagePreviewAdapter extends RecyclerView.Adapter<MessagePreviewAdapter.MessagePreviewViewHolder> {

    ArrayList<TransferMessagePreview> transferMessagePreviewsList;

    public MessagePreviewAdapter(ArrayList<TransferMessagePreview> transferMessagePreviewsList) {
        this.transferMessagePreviewsList = transferMessagePreviewsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MessagePreviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_personas, parent, false);
        return new MessagePreviewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return transferMessagePreviewsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MessagePreviewViewHolder holder, int position) {
        holder.name.setText(transferMessagePreviewsList.get(position).getUser());
        holder.last_message.setText(transferMessagePreviewsList.get(position).getMessage());
        // holder.photo.setImageResource(listaPersonas.get(position).getPhoto());
    }

    public class MessagePreviewViewHolder extends RecyclerView.ViewHolder {
        TextView name, last_message;
        ImageView photo;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MessagePreviewViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            last_message = (TextView) itemView.findViewById(R.id.last_message);
            photo = (ImageView) itemView.findViewById(R.id.Photo);
            photo.setClipToOutline(true);
        }
    }
}
