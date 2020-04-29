package com.logrolling.client;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
            dato.setText((CharSequence) pair.first);

            int negro = Color.parseColor("#222222");
            int blanco= Color.parseColor("#FFFFFF");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.TOP;


            if((Boolean) pair.second){
                dato.setBackgroundResource(R.drawable.this_user_message);
                dato.setTextColor(blanco);

                params.gravity = Gravity.RIGHT;
            }else{
                dato.setBackgroundResource(R.drawable.other_user_message);
                dato.setTextColor(negro);
                params.gravity = Gravity.LEFT;
            }

            dato.setLayoutParams(params);
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