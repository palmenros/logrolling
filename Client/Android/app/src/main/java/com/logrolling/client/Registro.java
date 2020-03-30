package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class Registro extends AppCompatActivity {
    private EditText usuario,contrasenna,repContrasenna;
    private Switch condiciones;
    private TextView numGrollies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario=(EditText)findViewById(R.id.NombreUsuario);
        contrasenna=(EditText)findViewById(R.id.Contrasenna);
        repContrasenna=(EditText)findViewById(R.id.RepContrasenna);
        condiciones=(Switch)findViewById(R.id.Condiciones);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
    }

    public void iniciarSesion(View view){
        Intent i=new Intent(this,InicioSesion.class);
        startActivity(i);

    }
    public void registrarse(View view){
        //Registro
        Intent i=new Intent(this,InicioSesion.class);
        startActivity(i);

    }
    public void mostrarPolíticaPrivacidad(View view){
        //Mostrar la política de privacidad
    }
}
