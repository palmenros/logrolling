package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatPersonActivity extends AppCompatActivity {
    private EditText writeMessage;
    private RecyclerView recycler;
    private TextView name;
    private List<Pair> dataList;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_persona);

        recycler=(RecyclerView)findViewById(R.id.ListaMensajes);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataList =new ArrayList<Pair>();
        for(int i=0;i<2;i++){
            dataList.add(i,Pair.create("Mensaje "+i,i%2==0?true:false));
        }
        adapter=new DataAdapter((ArrayList<Pair>) dataList);
        recycler.setAdapter(adapter);

        name =(TextView) findViewById(R.id.NombreChatTitulo);
        writeMessage =(EditText) findViewById(R.id.EscribirMensaje);

        recycler.scrollToPosition(dataList.size() - 1);
    }

    public void write(View view){
        String mensaje= writeMessage.getText().toString();
        if(mensaje.length()!=0){
            dataList.add(dataList.size(),Pair.create(mensaje,true));
            adapter.notifyDataSetChanged();
        }

        recycler.scrollToPosition(dataList.size() - 1);
        writeMessage.setText("");
    }


    //Panel Inferior
    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
        startActivity(i);
    }
    public void myFavors(View view) {
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }
    public void messages(View view) {
        Intent i = new Intent(this, MessageActivity.class);
        startActivity(i);

    }
    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);
    }
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }
}
