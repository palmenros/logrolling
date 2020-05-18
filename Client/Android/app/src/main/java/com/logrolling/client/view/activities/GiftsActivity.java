package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.Collections;

import com.logrolling.client.adapter.GiftsAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferGift;

public class GiftsActivity extends AppCompatActivity {
    private RecyclerView giftRecyclerView;
    private TextView numGrollies;
    private ArrayList<TransferGift> transferGifts = new ArrayList<TransferGift>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);
        giftRecyclerView = (RecyclerView) findViewById(R.id.ListaRegalos);
        giftRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        numGrollies = (TextView) findViewById(R.id.grollies);

        loadGrolliesAmount();
        fillGiftList();
    }

    private void loadGrolliesAmount() {
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

    private void buyGift(int position) {
        //TODO: Implement
        TransferGift gift = transferGifts.get(position);

        Controller controller = Controller.getInstance();
        controller.getCurrentUserGrollies((grollies) -> {

            if (grollies >= gift.getPrice()) {
                //Ask for address
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Introduce tu dirección postal");

                // Set up the input
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String address = input.getText().toString();

                        if (address.isEmpty()) {
                            new AlertDialog.Builder(GiftsActivity.this)
                                    .setTitle("Error")
                                    .setMessage("La dirección no puede ser vacía.")
                                    .setNeutralButton("Ok", (d, w) -> {
                                    }).show();
                        } else {
                            controller.purchaseGift(gift.getTitle(), address, () -> {
                                //Refresh remaining grollies
                                loadGrolliesAmount();
                            }, (error) -> {
                                new AlertDialog.Builder(GiftsActivity.this)
                                        .setTitle("Error de red")
                                        .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                                        .setNeutralButton("Ok", (d, w) -> {
                                            //Exit now
                                            android.os.Process.killProcess(android.os.Process.myPid());
                                            System.exit(1);
                                        }).show();
                            });
                        }

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            } else {
                //Not enough grollies
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No tienes suficientes grollies para comprar ese regalo")
                        .setNeutralButton("Ok", (dialog, which) -> {
                        })
                        .show();
            }

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

    private void fillGiftList() {
        Controller.getInstance().getAllGifts((gifts) -> {
            Collections.addAll(transferGifts, gifts);
            GiftsAdapter adapter = new GiftsAdapter(transferGifts, (view) -> {
                int position = giftRecyclerView.getChildLayoutPosition(view);
                buyGift(position);
            });

            giftRecyclerView.setAdapter(adapter);

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

    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

}
