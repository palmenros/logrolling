package com.logrolling.client.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;

public class ShopActivity extends AppCompatActivity {
    private TextView numGrollies;
    public TextView remaining;
    public double selectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        remaining = (TextView) findViewById(R.id.restantes);


        numGrollies = (TextView) findViewById(R.id.grollies);

        Controller.getInstance().getCurrentUserGrollies((grollies) -> {
            numGrollies.setText(Integer.valueOf(grollies).toString());
        }, (error) -> {
            new AlertDialog.Builder(this)
                           .setTitle("Error de red")
                           .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                           .setNeutralButton("Ok", (dialog, which) -> {
                               //Exit now
                               android.os.Process.killProcess(android.os.Process.myPid());
                               System.exit(1);
                           }).show();
        });


        selectedPrice = 0;
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
        selectedPrice = 0.99;
        buyGrolliesForSelectedPrice(view);
    }

    public void TwelveThousandGrollies(View view) {
        //Pagos por 4.99
        selectedPrice = 4.99;
        buyGrolliesForSelectedPrice(view);
    }

    public void FortyThousandGrollies(View view) {
        //Pagos por 14.99
        selectedPrice = 14.99;
        buyGrolliesForSelectedPrice(view);
    }

    public void NinetyThousandGrollies(View view) {
        //Pagos por 29.99
        selectedPrice = 29.99;
        buyGrolliesForSelectedPrice(view);
    }

    public void TwoHundredThousandGrollies(View view) {
        //Pagos por 59.99
        selectedPrice = 59.99;
        buyGrolliesForSelectedPrice(view);
    }

    public void buyGrolliesForSelectedPrice(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Seguro que quieres comprar " + Double.valueOf(selectedPrice).toString() + " grollies?")
                .setNegativeButton("No", (dialog, which)-> {
                    //No hacer nada
                })
                .setPositiveButton("Sí", (dialog, which) -> {
                    //TODO: Implementar compras
                }).show();
    }

}
