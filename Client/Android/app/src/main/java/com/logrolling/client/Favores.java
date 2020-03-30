package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Favores extends AppCompatActivity {
    private ListView listaFavores;
    private String[]favores={"Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra"};
    private boolean filtros;
    private ConstraintLayout constrainFiltros;
    private TextView minGrolliesText, minHorasText,maxDistanciaText;
    private SeekBar minGrolliesBar, minTiempoBar, maxDistanciaBar;
    public int minGrollies=10,minHoras=1;
    public double distancia=0.5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favores);

        filtros=false;
        constrainFiltros=(ConstraintLayout)findViewById(R.id.filtros);
        constrainFiltros.setVisibility(View.INVISIBLE);




        listaFavores=(ListView)findViewById(R.id.ListaMensajes);
        ArrayAdapter<String> adapterLista=new ArrayAdapter(this,R.layout.list_favores, favores);
        listaFavores.setAdapter(adapterLista);
        listaFavores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(Favores.this, RealizarFavor.class);
                startActivity(i);
            }
        });

        minGrolliesBar=(SeekBar)findViewById(R.id.minGrolliesBar);
        minTiempoBar=(SeekBar)findViewById(R.id.minTiempoBar);
        maxDistanciaBar=(SeekBar)findViewById(R.id.maxDistanciaBar);

        listeners_barras();
        minGrolliesText=(TextView)findViewById(R.id.MinGrollies);
        minHorasText=(TextView)findViewById(R.id.MinTiempo);
        maxDistanciaText=(TextView)findViewById(R.id.MaxDistancia);
    }

    //Panel Inferior
    public void regalos(View view) {
        Intent i = new Intent(this, Regalos.class);
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

    public void filtros(View view) {
        if(!filtros){
           constrainFiltros.setVisibility(View.VISIBLE);

        }else{
            constrainFiltros.setVisibility(View.INVISIBLE);
        }
        filtros=!filtros;
    }
    public void buscarFiltrado(View view){
        filtros(view);
        //Llamar a base de datos y cambiar el scrollView
    }
    public void listeners_barras(){

        minGrolliesBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minGrollies=((int)(2*Math.pow(progress, 2.5)))+10;

                if(progress==100){
                    minGrollies=200000;
                }
                if(minGrollies>100){
                    minGrollies=truncar(minGrollies);
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

        minTiempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minHoras=(int)(2.39*progress)+1;

                minHorasText.setText("Dentro de "+toHoras(minHoras));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        maxDistanciaBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String dist="";
                switch(progress){
                    case 0:
                        dist="500 m";
                        distancia=0.5;
                        break;
                    case 1:
                        dist="1 km";
                        distancia=1;
                        break;
                    case 2:
                        dist="2 km";
                        distancia=2;
                        break;
                    case 3:
                        dist="3 km";
                        distancia=3;
                        break;
                    case 4:
                        dist="5 km";
                        distancia=5;
                        break;
                    case 5:
                        dist="7 km";
                        distancia=7;
                        break;
                    case 6:
                        dist="10 km";
                        distancia=10;
                        break;
                    case 7:
                        dist="20 km";
                        distancia=20;
                        break;

                }
                maxDistanciaText.setText("En un radio de "+dist);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    String toHoras(double horas){
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
    int truncar(int n){
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
