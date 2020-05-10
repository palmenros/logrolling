package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class RegistrationAvtivity extends AppCompatActivity {
    private EditText user, password, repPassword;
    private Switch conditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user =(EditText)findViewById(R.id.NombreUsuario);
        password =(EditText)findViewById(R.id.Contrasenna);
        repPassword =(EditText)findViewById(R.id.RepContrasenna);
        conditions =(Switch)findViewById(R.id.Condiciones);

    }

    public void signIn(View view){
        Intent i=new Intent(this, SignInActivity.class);
        startActivity(i);

    }
    public void register(View view){
        //Registro
        Intent i=new Intent(this, SignInActivity.class);
        startActivity(i);

    }
    public void showPrivacyPolicy(View view){
        //Mostrar la política de privacidad
    }
}
