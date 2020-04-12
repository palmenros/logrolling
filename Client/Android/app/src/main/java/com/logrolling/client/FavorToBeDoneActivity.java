package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FavorToBeDoneActivity extends AppCompatActivity {

    private TextView numGrollies;
    private TextView name, description, deliveryLocation, deliveryDate, reward;
    private ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_que_realizar);

        numGrollies = (TextView) findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        name = (TextView) findViewById(R.id.nombre);
        description = (TextView) findViewById(R.id.descripcionFavor);
        deliveryLocation = (TextView) findViewById(R.id.lugarEntrega);
        deliveryDate = (TextView) findViewById(R.id.fechaLimite);
        reward = (TextView) findViewById(R.id.recompensa);
        photo = (ImageView) findViewById(R.id.Foto);



        //Ejemplo
        name.setText("Acercarse a la farmacia");
        description.setText("Necesito estos medicamentos: \n   o Paracetamol \n   o Gelocatil \n   o Dormidina \n   o Reflex");
        deliveryLocation.setText("Plaza de la Cebada Nº9");
        deliveryDate.setText("Dentro de 5 día y 14 horas");
        reward.setText("6000 grollies");

    }
    public void chat(View view) {
        Intent i = new Intent(this, ChatPersonActivity.class);
        startActivity(i);
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
