package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.view.CallableNetworkImageView;
import com.logrolling.client.web.WebRequestQueue;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import java.util.concurrent.Callable;

public class AskedFavorActivity extends AppCompatActivity {

    private boolean assigned;
    private Button chatButton, deleteComplete;
    private TextView numGrollies;
    private TextView name, description, deliveryLocation, deliveryDate, reward, worker;
    private CallableNetworkImageView photo;
    private boolean isImageFitToScreen = false;

    private TransferFavor transferFavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_favor);

        deleteComplete = (Button) findViewById(R.id.borrar);
        chatButton = (Button) findViewById(R.id.Chat);

        numGrollies = (TextView) findViewById(R.id.grollies);
        loadGrolliesAmount();

        name = (TextView) findViewById(R.id.nombre);
        description = (TextView) findViewById(R.id.descrFavor);
        deliveryLocation = (TextView) findViewById(R.id.lugarEntrega);
        deliveryDate = (TextView) findViewById(R.id.fechaLimite);
        reward = (TextView) findViewById(R.id.recompensa);

        worker = (TextView) findViewById(R.id.realizador);

        Bundle b = getIntent().getExtras();
        int favorId = b.getInt("favorId");

        photo = (CallableNetworkImageView) findViewById(R.id.foto);
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

                    new StfalconImageViewer.Builder<Bitmap>(AskedFavorActivity.this, images, new ImageLoader<Bitmap>() {
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

        Controller.getInstance().getFavorById(favorId, favor -> {
            transferFavor = favor;
            name.setText(favor.getTitle());
            description.setText(favor.getDescription());
            deliveryLocation.setText(LocationService.getInstance().getAddressFromCoordinates(transferFavor.getCoordinates()));
            deliveryDate.setText(favor.getFormattedDueTime());
            reward.setText(favor.getReward() + " grollies");
            assigned = favor.getWorker() != null;

            if (assigned) {
                deleteComplete.setText("Completar");
                worker.setText(favor.getWorker());
            } else {
                deleteComplete.setText("Borrar");
                worker.setText("No asignado");
                chatButton.setVisibility(View.GONE);
            }

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

    //Botones //Dependiendo de si el favor está assigned hacer una cosa u otra
    public void delete(View view) { //TENDRÁ QUE BORRAR

        if (assigned) {
            //Complete
            Controller.getInstance().completeFavor(transferFavor.getId(), () -> {
                new AlertDialog.Builder(this)
                        .setTitle("Éxito")
                        .setMessage("El favor ha sido completado.")
                        .setNeutralButton("Ok", (dialog, wich) -> {
                            Intent i = new Intent(this, MyFavorsActivity.class);
                            startActivity(i);
                        }).show();
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
        } else {
            //Delete
            new AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Seguro que quieres borrar el favor?")
                    .setPositiveButton("Sí", (dialog, wich) -> {
                        Controller.getInstance().deleteFavor(transferFavor.getId(), () -> {
                            Intent i = new Intent(this, MyFavorsActivity.class);
                            startActivity(i);
                        }, (error) -> {
                            new AlertDialog.Builder(this)
                                    .setTitle("Error de red")
                                    .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                                    .setNeutralButton("Ok", (d, w) -> {
                                        //Exit now
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }).show();
                        });
                    }).setNegativeButton("No", (dialog, which) -> {
            }).show();
        }


    }

    public void chat(View view) {
        if (transferFavor == null || !assigned) {
            return;
        }

        Intent i = new Intent(this, UserChatActivity.class);
        i.putExtra("otherUser", transferFavor.getWorker());
        startActivity(i);
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

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

}
