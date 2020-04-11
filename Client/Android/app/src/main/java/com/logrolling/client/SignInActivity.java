package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    private EditText user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        user =(EditText)findViewById(R.id.NombreUsuario);
        password =(EditText)findViewById(R.id.Contrasenna);
    }
    public void registration(View view){
        Intent i=new Intent(this, RegistrationAvtivity.class);
        startActivity(i);
    }
    public void signIn(View view){
        if(true ){//Habrá que comprobar que el user esta registrado
            Intent i=new Intent(this, FavorsActivity.class);
            startActivity(i);
        }
    }



}
