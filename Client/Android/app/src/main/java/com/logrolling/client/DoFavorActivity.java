package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoFavorActivity extends AppCompatActivity {
    private TextView name, description, deliveryLocation, deliveryDate, reward;
    private ImageView photo;
    private TextView numGrollies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_favor);
        name =(TextView)findViewById(R.id.nombre);
        description =(TextView)findViewById(R.id.descripcionFavor);
        deliveryLocation =(TextView)findViewById(R.id.lugarEntrega);
        deliveryDate =(TextView)findViewById(R.id.fechaLimite);
        reward =(TextView)findViewById(R.id.recompensa);
        photo =(ImageView)findViewById(R.id.Foto);


        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
        //Sets
        //Ejemplo
        name.setText("Ir a la compra");
        description.setText("Comprar los siguientes artículos por un precio total máximo de 10 €: \n\n  o 3kg de Naranjas \n  o Bolsa de patas \n  o Barra de pan \n  o Botella CocaCola 2l");
        deliveryLocation.setText("Paseo de los Melancólicos Nº 15");
        deliveryDate.setText("Dentro de 3 horas");
        reward.setText("500 grollies");

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


    public void doFavor(View view) {
        //realizar Favor
        Intent i = new Intent(this, FavorsActivity.class);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }



}
