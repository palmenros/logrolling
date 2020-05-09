package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    private TextView numGrollies;
    public TextView remaining;
    public double selectedPrice;
    private ConstraintLayout popUpConfirmation, popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        remaining =(TextView)findViewById(R.id.restantes);


        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
        selectedPrice = 0;
        popUpConfirmation=(ConstraintLayout)findViewById(R.id.PopUpConfirm);
        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError1);
        popUpConfirmation.setVisibility(View.INVISIBLE);
        popUpError.setVisibility(View.INVISIBLE);
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
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);

    }
    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);

    }
    public void TenGrollies(View view) {
        //Pedir videosRestantes=lo que sea
        /*
        if(videosRestantes<=0){
            //Mensaje de info
        }else{
            //Meter video
            remaining.setText("("+(videosRestantes-1)+" remaining)");
            //notificar a quien sea que ha gastado un video
        }
        */
    }
    public void TwoThousandGrollies(View view) {
       //Pagos por 0.99
        selectedPrice=0.99;
        showConfirmationPopUp(view);
    }
    public void TwelveThousandGrollies(View view) {
        //Pagos por 4.99
        selectedPrice=4.99;
        showConfirmationPopUp(view);
    }
    public void FortyThousandGrollies(View view) {
        //Pagos por 14.99
        selectedPrice=14.99;
        showConfirmationPopUp(view);
    }
    public void NinetyThousandGrollies(View view) {
        //Pagos por 29.99
        selectedPrice=29.99;
        showConfirmationPopUp(view);
    }
    public void TwoHundredThousandGrollies(View view) {
        //Pagos por 59.99
        selectedPrice=59.99;
        showConfirmationPopUp(view);
    }
    public void showConfirmationPopUp(View view){
        popUpConfirmation.setVisibility(View.VISIBLE);
    }
    public void closeConfirmationPopUp(View view){
        popUpConfirmation.setVisibility(View.INVISIBLE);
    }
    public void showErrorPopUp(View view){
        popUpError.setVisibility(View.VISIBLE);
    }
    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }
    public void confirmedShop(View view){
        closeConfirmationPopUp(view);
    }
}
