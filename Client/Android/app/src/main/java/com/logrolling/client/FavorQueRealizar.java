package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FavorQueRealizar extends AppCompatActivity {

    private TextView numGrollies;
    private TextView nombre, descripcion, lugarEntrega, fechaEntrega, recompensa;
    private ImageView fotografia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_que_realizar);

        numGrollies = (TextView) findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        nombre = (TextView) findViewById(R.id.nombre);
        descripcion = (TextView) findViewById(R.id.descripcionFavor);
        lugarEntrega = (TextView) findViewById(R.id.lugarEntrega);
        fechaEntrega = (TextView) findViewById(R.id.fechaLimite);
        recompensa = (TextView) findViewById(R.id.recompensa);
        fotografia = (ImageView) findViewById(R.id.Foto);



        //Ejemplo
        nombre.setText("Acercarse a la farmacia");
        descripcion.setText("Necesito estos medicamentos: \n   o Paracetamol \n   o Gelocatil \n   o Dormidina \n   o Reflex");
        lugarEntrega.setText("Plaza de la Cebada Nº9");
        fechaEntrega.setText("Dentro de 5 día y 14 horas");
        recompensa.setText("6000 grollies");

    }
    public void chatear(View view) {
        Intent i = new Intent(this, ChatPersona.class);
        startActivity(i);
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
