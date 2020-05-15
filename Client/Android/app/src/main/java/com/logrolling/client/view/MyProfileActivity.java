package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.logrolling.client.R;

public class MyProfileActivity extends AppCompatActivity {
    private EditText user, password, repPassword;
    private TextView numGrollies;
    private TextView popUpMessage;
    private ConstraintLayout popUpConfirmation, popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        user = (EditText) findViewById(R.id.NombreUsuario);
        password = (EditText) findViewById(R.id.Contrasenna);
        repPassword = (EditText) findViewById(R.id.RepContrasenna);

        numGrollies = (TextView) findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError7);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
        popUpConfirmation = (ConstraintLayout) findViewById(R.id.PopUpConfirm1);
        popUpConfirmation.setVisibility(View.INVISIBLE);
    }


    //Panel Inferior
    public void search(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    public void favors(View view) {
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


    public void guardarCambios(View view) {
       /* if(password.getText().toString().equals(repPassword.getText().toString())){//Condiciones en las que modificas el perfil
            //Enviar datos o lo que sea
        }*/
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);
    }

    public void cambiarFoto(View view) {

    }

    //popUpError
    public void showErrorPopUp(View view) {
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }

    public void showConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.VISIBLE);
    }

    public void closeConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.INVISIBLE);
    }
}
