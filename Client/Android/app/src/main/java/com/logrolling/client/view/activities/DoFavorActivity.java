package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.view.CallableNetworkImageView;
import com.logrolling.client.web.WebRequestQueue;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class DoFavorActivity extends AppCompatActivity {
    private TextView name, description, deliveryLocation, deliveryDate, reward;
    private CallableNetworkImageView photo;
    private TextView numGrollies;
    private int favorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_favor);
        name = (TextView) findViewById(R.id.nombre);
        description = (TextView) findViewById(R.id.descripcionFavor);
        deliveryLocation = (TextView) findViewById(R.id.lugarEntrega);
        deliveryDate = (TextView) findViewById(R.id.fechaLimite);
        reward = (TextView) findViewById(R.id.recompensa);

        numGrollies = (TextView) findViewById(R.id.grollies);
        loadGrolliesAmount();

        //Load favor

        Bundle b = getIntent().getExtras();
        favorId = b.getInt("favorId");

        photo = (CallableNetworkImageView) findViewById(R.id.Foto);
        photo.setClipToOutline(true);

        photo.setResponseObserver(new CallableNetworkImageView.ResponseObserver() {
            @Override
            public void onError() {
                photo.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                photo.setOnClickListener(view -> {

                    Bitmap[] images = {bitmap};

                    new StfalconImageViewer.Builder<Bitmap>(DoFavorActivity.this, images, new ImageLoader<Bitmap>() {
                        @Override
                        public void loadImage(ImageView imageView, Bitmap image) {
                            imageView.setImageBitmap(image);
                        }
                    }).show();
                });
            }
        });

        photo.setImageUrl(
                Controller.getInstance().getUncheckedFavorImageURL(favorId),
                WebRequestQueue.getInstance().getImageLoader()
        );

        Controller.getInstance().getFavorById(favorId,
                transferFavor -> {
                    name.setText(transferFavor.getTitle());
                    description.setText(transferFavor.getDescription());
                    deliveryLocation.setText(LocationService.getInstance().getAddressFromCoordinates(transferFavor.getCoordinates()));

                    PrettyTime prettyTime = new PrettyTime();
                    deliveryDate.setText(prettyTime.format(new Date(transferFavor.getDueTime() * 1000L)));
                    reward.setText(transferFavor.getReward() + " grollies");
                },
                error -> {
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

    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }


    public void doFavor(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Seguro que te comprometes a realizar este favor?")
                .setNegativeButton("No", (dialog, which) -> {
                })
                .setPositiveButton("Sí", (dialog, which) -> {
                    Controller.getInstance().doFavor(favorId, () -> {
                        Intent i = new Intent(this, SearchActivity.class);
                        startActivity(i);
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
                }).show();

    }

    public void doFavorConfirmed(View view) {
        //realizar Favor
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

}
