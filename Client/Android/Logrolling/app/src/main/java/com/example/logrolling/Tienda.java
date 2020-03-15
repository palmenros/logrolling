package com.example.logrolling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Tienda extends AppCompatActivity {

    public TextView restantes;
    private int videosRestantes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        restantes=(TextView)findViewById(R.id.restantes);
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
    public void regalos(View view) {
        Intent i = new Intent(this, Regalos.class);
        startActivity(i);

    }
    public void configuracion(View view) {
        Intent i = new Intent(this, Configuracion.class);
        startActivity(i);

    }
    public void DiezGrollies(View view) {
       //Meter video
        videosRestantes--;
        restantes.setText("("+videosRestantes+"restantes)");
    }
    public void DosMilGrollies(View view) {
       //Pagos por 0.99
    }
    public void DoceMilGrollies(View view) {
        //Pagos por 4.99
    }
    public void CuarentaMilGrollies(View view) {
        //Pagos por 14.99
    }
    public void NoventaMilGrollies(View view) {
        //Pagos por 29.99
    }
    public void DosCientosMil(View view) {
        //Pagos por 59.99
    }


}
