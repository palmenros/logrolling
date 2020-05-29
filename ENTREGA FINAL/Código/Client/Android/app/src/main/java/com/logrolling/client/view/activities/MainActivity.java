package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.services.PersistentStorageService;
import com.logrolling.client.web.WebRequestQueue;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_FINE_LOCATION = 13;
    private TextView popUpMessage;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Initialize services except location
        WebRequestQueue.createInstance(this);
        PersistentStorageService.createInstance(this);

        //Assure we have Fine Location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_FINE_LOCATION);

        } else {
            // Permission has already been granted
            onLocationPermissionGranted();
        }

        //TODO: Use NetworkImageView

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError14);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onLocationPermissionGranted();
            } else {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    //User has clicked on don't ask again, explain that it is necessary for apps working

                    new AlertDialog.Builder(this)
                            .setTitle("Permiso necesario")
                            .setMessage("El permiso de localización es necesario para el funcionamiento de Logrolling. Por favor, actívalo en ajustes y vuelve a entrar en la aplicación.")
                            .setNeutralButton("Ok", (dialog, which) -> {
                                //Exit now
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }).show();
                } else {
                    //Exit
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            }
        }
    }

    public void onLocationPermissionGranted() {
        LocationService.createInstance(this, () -> {

            //Entry point
            AuthenticationService.getInstance().tryStoredTokenAuth((authenticated) -> {
                if (authenticated) {
                    //Go to Explore Activity
                    Intent i = new Intent(this, SearchActivity.class);
                    startActivity(i);
                } else {
                    //Go to Log In Activity
                    Intent i = new Intent(this, SignInActivity.class);
                    startActivity(i);
                }
            }, (error) -> {
                //Network error, exit
                new AlertDialog.Builder(this)
                        .setTitle("Error de red")
                        .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                        .setNeutralButton("Ok", (dialog, which) -> {
                            //Exit now
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                        }).show();
            });

        }, (error) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Localización")
                    .setMessage("No se pudo obtener la localización del teléfono. Por favor, compruebe que está conectado.")
                    .setNeutralButton("Ok", (dialog, which) -> {
                        //Exit now
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    }).show();
        });

    }

    //popUpError
    public void showErrorPopUp(View view) {
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }
}
