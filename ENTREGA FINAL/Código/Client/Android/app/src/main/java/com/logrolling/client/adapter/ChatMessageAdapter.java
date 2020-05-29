package com.logrolling.client.adapter;

import android.graphics.Color;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logrolling.client.R;

import java.util.ArrayList;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder> {

    ArrayList<Pair> dataList;

    public ChatMessageAdapter(ArrayList<Pair> listaDatos) {
        this.dataList = listaDatos;
    }

    public class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView view;

        public ChatMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.IdDato);
        }

        public void dataAssignment(Pair pair) {
            view.setText((CharSequence) pair.first);

            int black = Color.parseColor("#222222");
            int white = Color.parseColor("#FFFFFF");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.TOP;


            if ((Boolean) pair.second) {
                view.setBackgroundResource(R.drawable.this_user_message);
                view.setTextColor(white);

                params.gravity = Gravity.RIGHT;
            } else {
                view.setBackgroundResource(R.drawable.other_user_message);
                view.setTextColor(black);
                params.gravity = Gravity.LEFT;
            }

            view.setLayoutParams(params);
        }
    }

    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, null, false);
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int i) {
        holder.dataAssignment(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}