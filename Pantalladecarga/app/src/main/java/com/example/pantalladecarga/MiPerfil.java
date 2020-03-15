package com.example.pantalladecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MiPerfil extends AppCompatActivity {
    private EditText usuario,correo,contrasenna,repContrasenna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        usuario=(EditText)findViewById(R.id.NombreUsuario);
        contrasenna=(EditText)findViewById(R.id.Contrasenna);
        correo=(EditText)findViewById(R.id.Correo);
        repContrasenna=(EditText)findViewById(R.id.RepContrasenna);
    }

    public void guardarCambios(View view){
        if(contrasenna.getText().toString().equals(repContrasenna.getText().toString())){//Condiciones en las que modificas el perfil
           //Enviar datos o lo que sea
        }
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
    public void configuracion(View view){
        Intent i=new Intent(this,Configuracion.class);
        startActivity(i);

    }
    public void regalos(View view){
        Intent i=new Intent(this,Regalos.class);
        startActivity(i);

    }

}
