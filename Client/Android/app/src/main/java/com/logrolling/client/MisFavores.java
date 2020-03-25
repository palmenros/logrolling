package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MisFavores extends AppCompatActivity {
    private ListView listaMisFavores;
    private String[]chats={"Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio",
            "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar",
            "Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_favores);
        listaMisFavores=(ListView)findViewById(R.id.listaFavoresMios);

        ArrayAdapter<String> adapter=new ArrayAdapter(this,R.layout.list_favores, chats);
        listaMisFavores.setAdapter(adapter);
        listaMisFavores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
            }
        });
    }

    public void favores(View view) {
        Intent i = new Intent(this, Favores.class);
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
        Intent i = new Intent(this, PedirFavor.class);
        startActivity(i);
    }

    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }
}
