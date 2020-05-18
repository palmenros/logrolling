package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.logrolling.client.adapter.FavorAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferFavor;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFavors;
    private ArrayList<TransferFavor> favorsArray = new ArrayList<TransferFavor>();
    private boolean filters;
    private ConstraintLayout constrainFilters;
    private TextView minGrolliesText, maxDistanceText, minTimeText, noResults;
    private SeekBar minGrolliesBar, maxDistanceBar, minTimeBar;

    //minTime representa numero de horas desde ahora
    public int minGrollies = 10, minTimeHoursFromNow = 1;
    public double maxDistance = 20;
    private TextView numGrollies;

    FavorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        numGrollies = (TextView) findViewById(R.id.grollies);
        noResults = (TextView) findViewById(R.id.sinFavores);

        Controller.getInstance().getCurrentUserGrollies(grollies -> {
            numGrollies.setText(Integer.valueOf(grollies).toString());
        }, error -> {
            new AlertDialog.Builder(this)
                    .setTitle("Error de red")
                    .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                    .setNeutralButton("Ok", (d, w) -> {
                        //Exit now
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }).show();
        });

        filters = false;
        constrainFilters = (ConstraintLayout) findViewById(R.id.Filtros);
        constrainFilters.setVisibility(View.INVISIBLE);

        recyclerViewFavors = (RecyclerView) findViewById(R.id.ListFavors);
        recyclerViewFavors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FavorAdapter(favorsArray, view -> {
            int position = recyclerViewFavors.getChildLayoutPosition(view);
            TransferFavor favor = favorsArray.get(position);

            Intent i = new Intent(this, DoFavorActivity.class);
            i.putExtra("favorId", favor.getId());
            startActivity(i);
        });
        recyclerViewFavors.setAdapter(adapter);

        refreshFavors();

        minGrolliesBar = (SeekBar) findViewById(R.id.minGrolliesBar);
        maxDistanceBar = (SeekBar) findViewById(R.id.maxDistanciaBar);
        minTimeBar = (SeekBar) findViewById(R.id.minTiempoBar);

        listeners_bars();
        minGrolliesText = (TextView) findViewById(R.id.MinGrollies);
        maxDistanceText = (TextView) findViewById(R.id.MaxDistancia);
        minTimeText = (TextView) findViewById(R.id.MinTiempo);
    }

    void refreshFavors() {

        int minDate = (int) (new Date().getTime() / 1000L) + 3600 * minTimeHoursFromNow;

        Controller.getInstance().getAvailableFavorsFiltered(minGrollies, maxDistance, minDate, favorList -> {
            favorsArray.clear();
            Collections.addAll(favorsArray, favorList);
            adapter.notifyDataSetChanged();

            if (favorList.length == 0) {
                //Show
                noResults.setVisibility(View.VISIBLE);
            } else {
                //Hide
                noResults.setVisibility(View.GONE);
            }

        }, error -> {
            new AlertDialog.Builder(this)
                    .setTitle("Error de red")
                    .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                    .setNeutralButton("Ok", (d, w) -> {
                        //Exit now
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }).show();
        });

    }

    //Panel Inferior
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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

    public void filters(View view) {
        if (!filters) {
            constrainFilters.setVisibility(View.VISIBLE);

        } else {
            constrainFilters.setVisibility(View.INVISIBLE);
        }
        filters = !filters;
    }

    public void findFilter(View view) {
        filters(view);
        refreshFavors();
    }

    public void listeners_bars() {

        minGrolliesBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minGrollies = ((int) (2 * Math.pow(progress, 2.5))) + 10;

                if (progress == 100) {
                    minGrollies = 200000;
                }
                if (minGrollies > 100) {
                    minGrollies = truncate(minGrollies);
                }

                String text = "";
                if (minGrollies >= 1000) {
                    text += minGrollies / 1000 + "." + (minGrollies % 1000 == 0 ? "000" : minGrollies % 1000) + " G";
                } else {
                    text += minGrollies + " G";
                }
                minGrolliesText.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        maxDistanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String dist = "";
                switch (progress) {
                    case 0:
                        dist = "500 m";
                        maxDistance = 0.5;
                        break;
                    case 1:
                        dist = "1 km";
                        maxDistance = 1;
                        break;
                    case 2:
                        dist = "2 km";
                        maxDistance = 2;
                        break;
                    case 3:
                        dist = "3 km";
                        maxDistance = 3;
                        break;
                    case 4:
                        dist = "5 km";
                        maxDistance = 5;
                        break;
                    case 5:
                        dist = "7 km";
                        maxDistance = 7;
                        break;
                    case 6:
                        dist = "10 km";
                        maxDistance = 10;
                        break;
                    case 7:
                        dist = "20 km";
                        maxDistance = 20;
                        break;

                }
                maxDistanceText.setText("En un radio de " + dist);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        minTimeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minTimeHoursFromNow = (int) (1 + 2.39 * progress);
                if (progress == 100)
                    minTimeHoursFromNow = 240;
                minTimeText.setText("Dentro de " + toHours(minTimeHoursFromNow));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    String toHours(double horas) {
        String dev = "";
        int dias, horass;
        dias = (int) horas / 24;
        horass = (int) horas % 24;
        if (dias >= 1) {
            dev += dias + " d  ";
        }
        if (horass >= 1) {
            dev += horass + " h ";
        }
        return dev;
    }

    int truncate(int n) {
        int dev = n;
        if (n > 100 && n <= 1000) {
            dev = (n / 10) * 10;
        } else if (n <= 10000) {
            dev = (n / 100) * 100;
        } else if (n <= 100000) {
            dev = (n / 1000) * 1000;
        } else if (n > 100000) {
            dev = (n / 10000) * 10000;
        }
        return dev;
    }
}
