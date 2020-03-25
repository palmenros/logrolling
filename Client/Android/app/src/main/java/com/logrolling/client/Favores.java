package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Favores extends AppCompatActivity {
    private ListView listaFavores;
    private String[]favores={"Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra","Apuntes","Perro","Compra"};
    private String [] distancias={"Menos de 1 kilómetro","Menos de 2 kilómetro", "De 2 a 5 kilómetros", "De 5 a 10 kilómetros", "Más de 10 kilómetros" };
    private Spinner distancia;
    private boolean filtros;
    private ConstraintLayout constrainFiltros;
    private TextView minMaxGrollies, minMaxHoras;
    private SeekBar minGrolliesBar,maxGrolliesBar, minTiempoBar, maxTiempoBar;
    public int minGrollies=10,maxGrollies=10,minHoras=1,maxHoras=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favores);

        filtros=false;
        constrainFiltros=(ConstraintLayout)findViewById(R.id.filtros);
        constrainFiltros.setVisibility(View.INVISIBLE);


        distancia=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner=new ArrayAdapter(this,R.layout.spinner_distancias, distancias);
        distancia.setAdapter(adapterSpinner);

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
        maxGrolliesBar=(SeekBar)findViewById(R.id.maxGrolliesBar);
        minTiempoBar=(SeekBar)findViewById(R.id.minTiempoBar);
        maxTiempoBar=(SeekBar)findViewById(R.id.maxTiempoBar);

        listeners_barras();
        minMaxGrollies=(TextView)findViewById(R.id.MinMaxGrollies);
        minMaxHoras=(TextView)findViewById(R.id.MinMaxTiempo);
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

                String texto="Min: ";
                if(minGrollies>=1000){
                    texto+=minGrollies/1000+"K G Max: ";
                }else{
                    texto+=minGrollies+" G Max: ";
                }
                if(maxGrollies>=1000){
                    texto+=maxGrollies/1000+"K G";
                }else{
                    texto+=maxGrollies+" G";
                }
                minMaxGrollies.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        maxGrolliesBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxGrollies=(int)(2*Math.pow(progress, 2.5))+10;
                if(progress==100){
                    maxGrollies=200000;
                }
                if(maxGrollies>100){
                    maxGrollies=truncar(maxGrollies);
                }
                String texto="Min: ";
                if(minGrollies>=1000){
                    texto+=minGrollies/1000+"K G Max: ";
                }else{
                    texto+=minGrollies+" G Max: ";
                }
                if(maxGrollies>=1000){
                    texto+=maxGrollies/1000+"K G";
                }else{
                    texto+=maxGrollies+" G";
                }
                minMaxGrollies.setText(texto);
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

                minMaxHoras.setText("Min: "+toHoras(minHoras)+" Max: "+toHoras(maxHoras));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        maxTiempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxHoras=(int)(2.39*progress)+1;
                minMaxHoras.setText("Min: "+toHoras(minHoras)+" Max: "+toHoras(maxHoras));
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
