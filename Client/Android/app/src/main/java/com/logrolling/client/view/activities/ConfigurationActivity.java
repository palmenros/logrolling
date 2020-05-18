package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.services.AuthenticationService;

public class ConfigurationActivity extends AppCompatActivity {
    private ConstraintLayout popUpSignOut;
    private TextView numGrollies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

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


    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    public void myProfile(View view) {
        Intent i = new Intent(this, MyProfileActivity.class);
        startActivity(i);
    }

    //SignOut
    public void signOut(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Seguro que quieres cerrar sesión?")
                .setNegativeButton("No", (dialog, which) -> {
                })
                .setPositiveButton("Si", (dialog, which) -> {
                    AuthenticationService.getInstance().signOut();
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }).show();

    }


}
