package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    private TextView numGrollies;
    public TextView remaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        remaining =(TextView)findViewById(R.id.restantes);


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
    }
    public void TwelveThousandGrollies(View view) {
        //Pagos por 4.99
    }
    public void FortyThousandGrollies(View view) {
        //Pagos por 14.99
    }
    public void NinetyThousandGrollies(View view) {
        //Pagos por 29.99
    }
    public void TwoHundredThousandGrollies(View view) {
        //Pagos por 59.99
    }


}
