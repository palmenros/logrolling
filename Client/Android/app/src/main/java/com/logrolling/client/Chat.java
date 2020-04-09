package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Chat extends AppCompatActivity{
    private EditText escribirMensaje;
    private ImageButton enviarMensaje;
    private ArrayList<Pair> listaDatos;
    private RecyclerView recycler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recycler=(RecyclerView)findViewById(R.id.ListaMensajes);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listaDatos=new ArrayList<Pair>();
        for(int i=0;i<10;i++){
            listaDatos.add(i,Pair.create("Mensaje"+i,true));
        }
        AdapterDatos adapter=new AdapterDatos(listaDatos);
        recycler.setAdapter(adapter);
    }

    public void escribir(View view){
        String mensaje=escribirMensaje.getText().toString();
        if(mensaje.length()==0){
            Toast.makeText(this,"No has escrito nada", Toast.LENGTH_LONG).show();
        }
        else{
            listaDatos.remove(0);
            listaDatos.add(Pair.create(mensaje,false));
        }
    }


    //Panel Inferior
    public void favores(View view) {
        Intent i = new Intent(this, Favores.class);
        startActivity(i);
    }
    public void misFavores(View view) {
        Intent i = new Intent(this, MisFavores.class);
        startActivity(i);
    }
    public void mensajes(View view) {
        Intent i = new Intent(this, Mensajes.class);
        startActivity(i);

    }
    public void configuracion(View view) {
        Intent i = new Intent(this, Configuracion.class);
        startActivity(i);
    }
    public void regalos(View view) {
        Intent i = new Intent(this, Regalos.class);
        startActivity(i);
    }

    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }
}
