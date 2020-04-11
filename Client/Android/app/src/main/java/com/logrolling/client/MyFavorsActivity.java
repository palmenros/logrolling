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

public class MyFavorsActivity extends AppCompatActivity {
    public int blue = Color.parseColor("#2699FB");
    public int white = Color.parseColor("#FFFFFF");
    private ListView listFavorsToBeDone, listDoneFavors;
    private TextView numGrollies;
    private Button favorsDo, favorsAsked;
    private String[] favorsDoneArray ={"Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio",
            "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar",
            "Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar"};
    private String[] favorsAskedArray ={"Pedidos", "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos",
            "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos", "Pedido2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_favores);

        favorsDo =(Button)findViewById(R.id.favoresARealizar);
        favorsAsked =(Button)findViewById(R.id.FavoresPedidos);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        listDoneFavors =(ListView)findViewById(R.id.listaFavoresPedidos);
        ArrayAdapter<String> adapterPedidos=new ArrayAdapter(this,R.layout.list_favores, favorsAskedArray);
        listDoneFavors.setAdapter(adapterPedidos);
        listDoneFavors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MyFavorsActivity.this, AskedFavorActivity.class);
                startActivity(i);
            }
        });
        listFavorsToBeDone =(ListView)findViewById(R.id.listaFavoresARealizar);
        ArrayAdapter<String> adapterARealizar=new ArrayAdapter(this,R.layout.list_favores, favorsDoneArray);
        listFavorsToBeDone.setAdapter(adapterARealizar);
        listFavorsToBeDone.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MyFavorsActivity.this, FavorToBeDoneActivity.class);
                startActivity(i);
            }
        });
        showAskedFavors();
    }

    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
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

    public void askFavor(View view) {
        Intent i = new Intent(this, AskFavorActivity.class);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }


    private void showAskedFavors(){
        favorsAsked.setBackgroundColor(blue);
        favorsAsked.setTextColor(white);
        favorsDo.setBackgroundColor(white);
        favorsDo.setTextColor(blue);
        listFavorsToBeDone.setVisibility(View.INVISIBLE);
        listDoneFavors.setVisibility(View.VISIBLE);
    }
    public void showAskedFavors(View view){
        showAskedFavors();
    }
    public void showFavorsToBeDone(View view){

        favorsAsked.setBackgroundColor(white);
        favorsAsked.setTextColor(blue);
        favorsDo.setBackgroundColor(blue);
        favorsDo.setTextColor(white);
        listFavorsToBeDone.setVisibility(View.VISIBLE);
        listDoneFavors.setVisibility(View.INVISIBLE);
    }
}
