package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RealizarFavor extends AppCompatActivity {
    private TextView nombre, descripcion, lugarRealizacion, lugarEntrega, fechaEntrga, recompensa;
    private ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_favor);
        nombre=(TextView)findViewById(R.id.Nombre);
        descripcion=(TextView)findViewById(R.id.DescripcionFavor);
        lugarRealizacion=(TextView)findViewById(R.id.lugarRealizacion);
        lugarEntrega=(TextView)findViewById(R.id.lugarEntrega);
        fechaEntrga=(TextView)findViewById(R.id.FechaLimite);
        recompensa=(TextView)findViewById(R.id.Recompensa);
        foto=(ImageView)findViewById(R.id.Foto);

        //Sets
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


    public void realizarFavor(View view) {
        //realizar Favor
        Intent i = new Intent(this, Favores.class);
        startActivity(i);
    }

    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }



}
