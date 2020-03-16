package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InicioSesion extends AppCompatActivity {
    private EditText usuario, contrasenna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        usuario=(EditText)findViewById(R.id.NombreUsuario);
        contrasenna=(EditText)findViewById(R.id.Contrasenna);
    }
    public void registrarse(View view){
        Intent i=new Intent(this, Registro.class);
        startActivity(i);
    }
    public void iniciarSesion(View view){
        if(true ){//Habrá que comprobar que el usuario esta registrado
            Intent i=new Intent(this, Favores.class);
            startActivity(i);
        }
    }



}
