package com.example.pantalladecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PantallaInicial extends AppCompatActivity {
    private EditText usuario, contrasenna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
        usuario=(EditText)findViewById(R.id.NombreUsuario);
        contrasenna=(EditText)findViewById(R.id.Contrasenna);
    }
    public void registrarse(View view){
        Intent i=new Intent(this,Registro.class);
        startActivity(i);
    }
    public void iniciarSesion(View view){
        if(true ){//Habrá qeu comprobar que el usuario esta registrado
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        }
    }




}
