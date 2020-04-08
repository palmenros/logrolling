package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FavorPedido extends AppCompatActivity {

    boolean adjudicado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Botones //Dependiendo de si el favor está adjudicado hacer una cosa u otra
    public void borrar(View view) { //TENDRÁ QUE BORRAR
        Intent i = new Intent(this, MisFavores.class);
        startActivity(i);
    }
    public void editar(View view) { //TENDRÁ QUE HABILITAR LA OPCION DE EDITAR

    }

    public void chat(View view) { //TENDRÁ QUE LLEVAR A CHAT CON UNA PERSONA DETERMINADA

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
