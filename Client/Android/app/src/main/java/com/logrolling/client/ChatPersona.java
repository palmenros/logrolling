package com.logrolling.client;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ChatPersona extends AppCompatActivity {
    private EditText escribirMensaje;
    private RecyclerView recycler;
    private TextView nombre;
    private List<Pair> listaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_persona);

        recycler=(RecyclerView)findViewById(R.id.ListaMensajes);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listaDatos=new ArrayList<Pair>();
        for(int i=0;i<2;i++){
            listaDatos.add(i,Pair.create("Mensaje"+i,i%2==0?true:false));
        }

        AdapterDatos adapter=new AdapterDatos((ArrayList<Pair>) listaDatos);
        recycler.setAdapter(adapter);

        nombre=(TextView) findViewById(R.id.NombreChatTitulo);
        escribirMensaje=(EditText) findViewById(R.id.EscribirMensaje);
        //Ejemplo
        nombre.setText("Manolo");
    }

    public void escribir(View view){
        String mensaje=escribirMensaje.getText().toString();
        if(mensaje.length()!=0){
            listaDatos.add(listaDatos.size(),Pair.create(mensaje,true));
        }
        escribirMensaje.setText("");
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
