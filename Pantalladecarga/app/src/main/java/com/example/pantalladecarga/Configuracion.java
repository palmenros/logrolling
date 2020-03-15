package com.example.pantalladecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
    }

    public void favores(View view){
        Intent i=new Intent(this,Favores.class);
        startActivity(i);

    }
    public void misFavores(View view){
        Intent i=new Intent(this,MisFavores.class);
        startActivity(i);

    }
    public void mensajes(View view){
        Intent i=new Intent(this,Mensajes.class);
        startActivity(i);

    }

    public void perfil(View view){
        Intent i=new Intent(this,MiPerfil.class);
        startActivity(i);

    }
    public void notificaciones(View view){
      //notificaciones
    }
    public void invitaAmigo(View view){
       //InvitarAmigo

    }
    public void mostrarAyuda(View view){
       //MostrarAyuda

    }


}
