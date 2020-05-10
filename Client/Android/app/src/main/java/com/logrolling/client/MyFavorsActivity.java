package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyFavorsActivity extends AppCompatActivity {
    public int blue = Color.parseColor("#2699FB");
    public int white = Color.parseColor("#FFFFFF");
    private RecyclerView listFavorsToBeDone, listDoneFavors;
    private TextView numGrollies;
    private Button favorsDo, favorsAsked;
    private ArrayList<Favor> favorsDoneArray = new ArrayList<Favor>(); /*={"Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio",
            "Gestiones administrativas", "Planchar","Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar",
            "Comprar pan", "Pasar apuntes a limpio", "Gestiones administrativas", "Planchar"};*/
    private ArrayList<Favor> favorsAskedArray = new ArrayList<Favor>();/*={"Pedidos", "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos",
            "Pedido2", "Pedidos","Pedido","Pedidos", "Pedido", "Pedidos", "Pedido2", "Pedidos", "Pedido2"};*/

    private TextView popUpMessage;
    private ConstraintLayout popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favores);

        favorsDo =(Button)findViewById(R.id.favoresARealizar2);
        favorsAsked =(Button)findViewById(R.id.favoresPedidos);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        listDoneFavors =(RecyclerView)findViewById(R.id.ListaFavoresPedidos);
        listDoneFavors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        llenarLista();

        AdapterFavores adapterPedidos=new AdapterFavores(favorsAskedArray);
        listDoneFavors.setAdapter(adapterPedidos);
       /* listDoneFavors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MyFavorsActivity.this, AskedFavorActivity.class);
                startActivity(i);
            }
        });*/

        listFavorsToBeDone =(RecyclerView)findViewById(R.id.ListaFavoresARealizar);
        listFavorsToBeDone.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        AdapterFavores adapterARealizar=new AdapterFavores(favorsDoneArray);
        listFavorsToBeDone.setAdapter(adapterARealizar);
        /*listFavorsToBeDone.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MyFavorsActivity.this, FavorToBeDoneActivity.class);
                startActivity(i);
            }
        });*/
        showAskedFavors();

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError12);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);
    }
    private void llenarLista(){
        for(int i=0;i<10;i++) {
            favorsDoneArray.add(new Favor("Nombre "+i,"Descripcion "+i,"Direccion "+i,"Favor "+i,R.drawable.ic_person_black_24dp,i*1000, "Hasta mañana a las 12:00", "A 150m de ti"));
            favorsAskedArray.add(new Favor("Nombre "+i,"Descripcion "+i,"Direccion "+i,"Favor "+i,R.drawable.ic_person_black_24dp,i*1000, "Hasta mañana a las 12:00", "A 150m de ti"));
        }
    }

    public void search(View view) {
        Intent i = new Intent(this, SearchActivity.class);
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

    //popUpError
    public void showErrorPopUp(View view){
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }
    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }

}
