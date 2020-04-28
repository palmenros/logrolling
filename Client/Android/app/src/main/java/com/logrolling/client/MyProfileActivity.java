package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {
    private EditText user, password, repPassword;
    private TextView numGrollies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        user =(EditText)findViewById(R.id.NombreUsuario);
        password =(EditText)findViewById(R.id.Contrasenna);
        repPassword =(EditText)findViewById(R.id.RepContrasenna);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
    }



    //Panel Inferior
    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
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
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }



    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }


    public void guardarCambios(View view){
       /* if(password.getText().toString().equals(repPassword.getText().toString())){//Condiciones en las que modificas el perfil
            //Enviar datos o lo que sea
        }*/
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);
    }
    public void cambiarFoto(View view){

    }
}
