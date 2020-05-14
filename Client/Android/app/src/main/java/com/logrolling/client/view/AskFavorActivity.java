package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.logrolling.client.R;

public class AskFavorActivity extends AppCompatActivity {
    private TextView numGrollies;
    private TextView popUpMessage;

    private EditText name, description, deliveryLocation, deliveryDate, reward;
    private ConstraintLayout popUpConfirmation, popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError9);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);
        popUpConfirmation=(ConstraintLayout)findViewById(R.id.PopUpConfirm4);
        popUpConfirmation.setVisibility(View.INVISIBLE);


        name=(EditText)findViewById(R.id.Nombre);
        description=(EditText)findViewById(R.id.DescripcionFavor);
        deliveryLocation=(EditText)findViewById(R.id.LugarEntrega);
        deliveryDate=(EditText)findViewById(R.id.FechaLimite);
        reward=(EditText)findViewById(R.id.Recompensa);
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


    public void askFavor(View view) {
        //Comprobar que la informacion del favor es correcta
        //(name, description, deliveryLocation, deliveryDate, reward)
        showConfirmationPopUp(view);
    }
    public void askFavorConfirmed(View view) {
        //pedirFavor
        closeConfirmationPopUp(view);
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }
    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }
    public void addPhoto(View view) {
        //Añadir foto
    }
    public void editPhoto(View view) {
        //Añadir foto
    }
    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }
    public void showConfirmationPopUp(View view){
        popUpConfirmation.setVisibility(View.VISIBLE);
    }
    public void closeConfirmationPopUp(View view){ popUpConfirmation.setVisibility(View.INVISIBLE);}
    public void showErrorPopUp(View view){
        popUpError.setVisibility(View.VISIBLE);
    }
}
