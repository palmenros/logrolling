package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ShopActivity extends AppCompatActivity {
    private TextView numGrollies;
    public TextView remaining;
    public int selectedPrice;

    private static final int REQUEST_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        remaining = (TextView) findViewById(R.id.restantes);
        numGrollies = (TextView) findViewById(R.id.grollies);

        updateUserGrollies();

        selectedPrice = 0;
    }

    void updateUserGrollies() {
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
    }


    //Panel Inferior
    public void search(View view) {
        Intent i = new Intent(this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void favors(View view) {
        Intent i = new Intent(this, MyFavorsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void messages(View view) {
        Intent i = new Intent(this, MessageActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);

    }

    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);

    }

    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
        selectedPrice = 99;
        buyGrolliesForSelectedPrice(view);
    }

    public void TwelveThousandGrollies(View view) {
        //Pagos por 4.99
        selectedPrice = 499;
        buyGrolliesForSelectedPrice(view);
    }

    public void FortyThousandGrollies(View view) {
        //Pagos por 14.99
        selectedPrice = 1499;
        buyGrolliesForSelectedPrice(view);
    }

    public void NinetyThousandGrollies(View view) {
        //Pagos por 29.99
        selectedPrice = 2999;
        buyGrolliesForSelectedPrice(view);
    }

    public void TwoHundredThousandGrollies(View view) {
        //Pagos por 59.99
        selectedPrice = 5999;
        buyGrolliesForSelectedPrice(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);

                //Send NONCE to server and wait
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Procesando...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();

                Controller.getInstance().makeTransaction(selectedPrice, nonce.getNonce(), () -> {
                    progressDialog.dismiss();

                    new AlertDialog.Builder(this)
                            .setTitle("Éxito")
                            .setMessage("Pago realizado con éxito.")
                            .setNeutralButton("Ok", (d, w) -> {}).show();

                    updateUserGrollies();

                }, (error) -> {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Error al tramitar el pago. Inténtalo en unos instantes.")
                            .setNeutralButton("Ok", (d, w) -> {}).show();
                });

            } else if (resultCode == RESULT_CANCELED) {
                new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Pago cancelado.")
                            .setNeutralButton("Ok", (d, w) -> {}).show();
            } else {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Error al tramitar el pago. Inténtalo en unos instantes.")
                            .setNeutralButton("Ok", (d, w) -> {}).show();
            }
        }
    }

    public void buyGrolliesForSelectedPrice(View view) {
        DecimalFormat df = new DecimalFormat("#.##");


        new AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage( String.format( "¿Seguro que quieres comprar grollies por " + df.format(selectedPrice / 100d) + "€ ?"))
                .setNegativeButton("No", (dialog, which)-> {
                    //No hacer nada
                })
                .setPositiveButton("Sí", (dialog, which) -> {

                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Procesando...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    Controller.getInstance().getPaymentClientToken(token -> {
                        progressDialog.dismiss();
                        DropInRequest dropInRequest = new DropInRequest()
                        .clientToken(token);

                        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
                    }, error -> {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Error al tramitar el pago. Inténtalo en unos instantes.")
                            .setNeutralButton("Ok", (d, w) -> {}).show();
                    });

                }).show();
    }

}
