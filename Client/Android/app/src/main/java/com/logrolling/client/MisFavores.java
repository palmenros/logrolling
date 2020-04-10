package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MisFavores extends AppCompatActivity {
    public int azul= Color.parseColor("#2699FB");
    public int blanco= Color.parseColor("#FFFFFF");
    private ListView listaFavoresARealizar,listaFavoresPedidos;
    private TextView numGrollies;
    private Button favoresRealizar, favoresPedidos;
    private String[]favoresReal={"Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio",
            "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar",
            "Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar"};
    private String[]favoresPed={"Pedidos", "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos",
            "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos", "Pedido2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_favores);

        favoresRealizar=(Button)findViewById(R.id.favoresARealizar);
        favoresPedidos=(Button)findViewById(R.id.FavoresPedidos);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        listaFavoresPedidos=(ListView)findViewById(R.id.listaFavoresPedidos);
        ArrayAdapter<String> adapterPedidos=new ArrayAdapter(this,R.layout.list_favores, favoresPed);
        listaFavoresPedidos.setAdapter(adapterPedidos);
        listaFavoresPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MisFavores.this, FavorPedido.class);
                startActivity(i);
            }
        });
        listaFavoresARealizar=(ListView)findViewById(R.id.listaFavoresARealizar);
        ArrayAdapter<String> adapterARealizar=new ArrayAdapter(this,R.layout.list_favores, favoresReal);
        listaFavoresARealizar.setAdapter(adapterARealizar);
        listaFavoresARealizar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MisFavores.this, FavorQueRealizar.class);
                startActivity(i);
            }
        });
        mostrarFavoresPedidos();
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


    private void mostrarFavoresPedidos(){
        favoresPedidos.setBackgroundColor(azul);
        favoresPedidos.setTextColor(blanco);
        favoresRealizar.setBackgroundColor(blanco);
        favoresRealizar.setTextColor(azul);
        listaFavoresARealizar.setVisibility(View.INVISIBLE);
        listaFavoresPedidos.setVisibility(View.VISIBLE);
    }
    public void mostrarFavoresPedidos(View view){
        mostrarFavoresPedidos();
    }
    public void mostrarFavoresARealizar(View view){

        favoresPedidos.setBackgroundColor(blanco);
        favoresPedidos.setTextColor(azul);
        favoresRealizar.setBackgroundColor(azul);
        favoresRealizar.setTextColor(blanco);
        listaFavoresARealizar.setVisibility(View.VISIBLE);
        listaFavoresPedidos.setVisibility(View.INVISIBLE);
    }
}
