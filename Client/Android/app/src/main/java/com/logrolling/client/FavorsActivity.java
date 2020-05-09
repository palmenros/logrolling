package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class FavorsActivity extends AppCompatActivity {
    private RecyclerView listFavors;
    private ArrayList<Favor> favorsArray = new ArrayList<Favor>(); // ={"Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra"};
    private boolean filters;
    private ConstraintLayout constrainFilters;
    private TextView minGrolliesText, maxDistanceText, popUpMessage;
    private SeekBar minGrolliesBar, maxDistanceBar;
    public int minGrollies=10;
    public double distance =0.5;
    private ConstraintLayout popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favores);

        filters =false;
        constrainFilters =(ConstraintLayout)findViewById(R.id.filtros);
        constrainFilters.setVisibility(View.INVISIBLE);

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError4);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);

        llenarLista();

        listFavors =(RecyclerView)findViewById(R.id.ListFavors);
        listFavors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        AdapterFavores adapterLista=new AdapterFavores(favorsArray);
        listFavors.setAdapter(adapterLista);

       /* listFavors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(FavorsActivity.this, DoFavorActivity.class);
                startActivity(i);
            }
        });*/

        minGrolliesBar=(SeekBar)findViewById(R.id.minGrolliesBar);
        maxDistanceBar =(SeekBar)findViewById(R.id.maxDistanciaBar);

        listeners_bars();
        minGrolliesText=(TextView)findViewById(R.id.MinGrollies);
        maxDistanceText =(TextView)findViewById(R.id.MaxDistancia);
    }

    private void llenarLista(){
        for(int i=0;i<10;i++) {
            favorsArray.add(new Favor("Nombre "+i, "Descripcion "+i, "Direccion "+i, "Favor "+i, R.drawable.ic_person_black_24dp, i*1000, "Hasta mañana a las 12:00", "A 150m de ti"));
        }
    }
    //Panel Inferior
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
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



    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    public void filters(View view) {
        if(!filters){
           constrainFilters.setVisibility(View.VISIBLE);

        }else{
            constrainFilters.setVisibility(View.INVISIBLE);
        }
        filters =!filters;
    }
    public void findFilter(View view){
        filters(view);
        //Llamar a base de datos y cambiar el scrollView
    }
    public void listeners_bars(){

        minGrolliesBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minGrollies=((int)(2*Math.pow(progress, 2.5)))+10;

                if(progress==100){
                    minGrollies=200000;
                }
                if(minGrollies>100){
                    minGrollies= truncate(minGrollies);
                }

                String text="";
                if(minGrollies>=1000){
                    text+=minGrollies/1000+"."+(minGrollies%1000==0?"000":minGrollies%1000)+" G";
                }else{
                    text+=minGrollies+" G";
                }
                minGrolliesText.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        maxDistanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String dist="";
                switch(progress){
                    case 0:
                        dist="500 m";
                        distance =0.5;
                        break;
                    case 1:
                        dist="1 km";
                        distance =1;
                        break;
                    case 2:
                        dist="2 km";
                        distance =2;
                        break;
                    case 3:
                        dist="3 km";
                        distance =3;
                        break;
                    case 4:
                        dist="5 km";
                        distance =5;
                        break;
                    case 5:
                        dist="7 km";
                        distance =7;
                        break;
                    case 6:
                        dist="10 km";
                        distance =10;
                        break;
                    case 7:
                        dist="20 km";
                        distance =20;
                        break;

                }
                maxDistanceText.setText("En un radio de "+dist);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //popUpError
    public void showErrorPopUp(View view){
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }
    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }

    String toHours(double horas){
        String dev="";
        int dias,horass;
        dias=(int)horas/24;
        horass=(int)horas%24;
        if(dias>=1){
            dev+=dias+" d  ";
        }
        if(horass>=1){
            dev+=horass+" h ";
        }
        return dev;
    }
    int truncate(int n){
        int dev=n;
        if(n>100 && n<=1000){
            dev=(n/10)*10;
        }else if(n<=10000){
            dev=(n/100)*100;
        }else if(n<=100000){
            dev=(n/1000)*1000;
        }else if(n>100000){
            dev=(n/10000)*10000;
        }
        return dev;
    }
}
