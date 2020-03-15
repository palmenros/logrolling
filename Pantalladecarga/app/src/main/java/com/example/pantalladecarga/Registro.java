package com.example.pantalladecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class Registro extends AppCompatActivity {
    private EditText usuario,correo,contrasenna,repContrasenna;
    private Switch condiciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario=(EditText)findViewById(R.id.NombreUsuario);
        contrasenna=(EditText)findViewById(R.id.Contrasenna);
        correo=(EditText)findViewById(R.id.Correo);
        repContrasenna=(EditText)findViewById(R.id.RepContrasenna);
        condiciones=(Switch)findViewById(R.id.Condiciones);
    }

    public void iniciarSesion(View view){
        Intent i=new Intent(this,PantallaInicial.class);
        startActivity(i);

    }
    public void mostrarPolíticaPrivacidad(View view){

    }
}
