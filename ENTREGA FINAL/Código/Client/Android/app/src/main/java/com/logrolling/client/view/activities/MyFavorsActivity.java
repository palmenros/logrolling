package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.Collections;

import com.logrolling.client.adapter.AskedFavorAdapter;
import com.logrolling.client.adapter.FavorAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferFavor;

public class MyFavorsActivity extends AppCompatActivity {
    public int blue = Color.parseColor("#2699FB");
    public int white = Color.parseColor("#FFFFFF");
    private RecyclerView favorsToBeDoneRecyclerView, askedFavorsRecyclerView;
    private TextView numGrollies;
    private Button favorsDo, favorsAsked;
    private ArrayList<TransferFavor> favorsDoneArray = new ArrayList<TransferFavor>();
    private ArrayList<TransferFavor> favorsAskedArray = new ArrayList<TransferFavor>();
    private TextView emptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favors);

        favorsDo = (Button) findViewById(R.id.favoresARealizar2);
        favorsAsked = (Button) findViewById(R.id.favoresPedidos);
        emptyTextView = (TextView) findViewById(R.id.textView3);
        numGrollies = (TextView) findViewById(R.id.grollies);
        loadGrolliesAmount();

        askedFavorsRecyclerView = (RecyclerView) findViewById(R.id.ListaFavoresPedidos);
        askedFavorsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fillList();

        favorsToBeDoneRecyclerView = (RecyclerView) findViewById(R.id.ListaFavoresARealizar);
        favorsToBeDoneRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

    private void fillList() {

        Controller controller = Controller.getInstance();

        controller.getFavorsToBeDone(favorsToBeDone -> {
            controller.getCreatedFavors(favorsAsked -> {
                Collections.addAll(favorsDoneArray, favorsToBeDone);
                Collections.addAll(favorsAskedArray, favorsAsked);

                AskedFavorAdapter askedAdapter = new AskedFavorAdapter(favorsAskedArray, (view) -> {
                    //On click asked
                    int position = askedFavorsRecyclerView.getChildLayoutPosition(view);
                    TransferFavor favor = favorsAskedArray.get(position);

                    //Go to AskedFavorActivity
                    int id = favor.getId();
                    Intent i = new Intent(this, AskedFavorActivity.class);
                    i.putExtra("favorId", favor.getId());
                    startActivity(i);
                });
                askedFavorsRecyclerView.setAdapter(askedAdapter);

                FavorAdapter toBeDoneAdapter = new FavorAdapter(favorsDoneArray, (view) -> {
                    //On click done
                    int position = favorsToBeDoneRecyclerView.getChildLayoutPosition(view);
                    TransferFavor favor = favorsDoneArray.get(position);

                    //Go to FavorToBeDoneActivity
                    int id = favor.getId();
                    Intent i = new Intent(this, FavorToBeDoneActivity.class);
                    i.putExtra("favorId", favor.getId());
                    startActivity(i);

                });
                favorsToBeDoneRecyclerView.setAdapter(toBeDoneAdapter);
                showAskedFavors();
            }, error -> {
                new AlertDialog.Builder(this)
                        .setTitle("Error de red")
                        .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                        .setNeutralButton("Ok", (dialog, which) -> {
                            //Exit now
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }).show();
            });

        }, error -> {
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

    public void search(View view) {
        Intent i = new Intent(this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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

    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);

    }

    public void askFavor(View view) {
        Intent i = new Intent(this, AskFavorActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }


    private void showAskedFavors() {
        favorsAsked.setBackgroundColor(blue);
        favorsAsked.setTextColor(white);
        favorsDo.setBackgroundColor(white);
        favorsDo.setTextColor(blue);
        favorsToBeDoneRecyclerView.setVisibility(View.INVISIBLE);
        askedFavorsRecyclerView.setVisibility(View.VISIBLE);

        if (favorsAskedArray.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText("No has pedido ningún favor");
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    public void showAskedFavors(View view) {
        showAskedFavors();
    }

    public void showFavorsToBeDone(View view) {

        favorsAsked.setBackgroundColor(white);
        favorsAsked.setTextColor(blue);
        favorsDo.setBackgroundColor(blue);
        favorsDo.setTextColor(white);
        favorsToBeDoneRecyclerView.setVisibility(View.VISIBLE);
        askedFavorsRecyclerView.setVisibility(View.INVISIBLE);

        if (favorsDoneArray.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText("No tienes ningún favor");
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

}
