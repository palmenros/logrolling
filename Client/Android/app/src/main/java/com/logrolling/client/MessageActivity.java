package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView listChat;
    private ArrayList<Persona> chats = new ArrayList<Persona>();
    private TextView numGrollies;
    private AdapterPersonas adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//TODO: Pedir el número de grollies a quien sea

        listChat =(RecyclerView)findViewById(R.id.ListaMensajes);
        listChat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        llenarLista();

        adapter = new AdapterPersonas(chats);
        listChat.setAdapter(adapter);

        /*listChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MessageActivity.this, MessageActivity.class);
                startActivity(i);
            }
        });*/
    }

    private void llenarLista(){
        for(int i=0;i<10;i++) {
            chats.add(new Persona("Persona "+i,"ultimo mensaje",R.drawable.ic_person_black_24dp));
        }
    }

    //Panel Inferior
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }
    public void myFavors(View view) {
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }
    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
        startActivity(i);

    }
    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);
    }



    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }


}
