package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Regalos extends AppCompatActivity {
    private ListView listaFavores;
    private TextView numGrollies;
    private String[]regalos={"Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox",
            "Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regalos);
        listaFavores=(ListView)findViewById(R.id.ListaMensajes);


        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea


        ArrayAdapter<String> adapter=new ArrayAdapter(this,R.layout.list_regalos, regalos);
        listaFavores.setAdapter(adapter);
        listaFavores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
            }
        });
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



    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }


}
