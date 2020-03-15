package com.example.logrolling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PedirFavor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);
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


    public void pedirFavor(View view) {
        //pedirFavor
        Intent i = new Intent(this, MisFavores.class);
        startActivity(i);
    }

    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }
    public void annadirFoto(View view) {
        //Añadir foto
    }


}
