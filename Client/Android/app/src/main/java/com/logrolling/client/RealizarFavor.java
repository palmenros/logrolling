package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RealizarFavor extends AppCompatActivity {
    private TextView nombre, descripcion, lugarEntrega, fechaEntrega, recompensa;
    private ImageView foto;
    private TextView numGrollies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_favor);
        nombre=(TextView)findViewById(R.id.nombre);
        descripcion=(TextView)findViewById(R.id.descripcionFavor1);
        lugarEntrega=(TextView)findViewById(R.id.LugarEntrega1);
        fechaEntrega=(TextView)findViewById(R.id.FechaLimite);
        recompensa=(TextView)findViewById(R.id.Recompensa);
        foto=(ImageView)findViewById(R.id.Foto);


        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
        //Sets
        //Ejemplo
        nombre.setText("Ir a la compra");
        descripcion.setText("Comprar los siguientes artículos por un precio total máximo de 10 €: \n\n  o 3kg de Naranjas \n  o Bolsa de patas \n  o Barra de pan \n  o Botella CocaCola 2l");
        lugarEntrega.setText("Paseo de los Melancólicos Nº 15");
        fechaEntrega.setText("Dentro de 3 horas");
        recompensa.setText("500 grollies");

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
