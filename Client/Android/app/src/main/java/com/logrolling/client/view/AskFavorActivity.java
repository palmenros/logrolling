package com.logrolling.client.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.transfer.Coordinates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AskFavorActivity extends AppCompatActivity {
    private TextView numGrollies;
    private TextView popUpMessage;

    private Button dueTimeButton, deliveryLocationButton;

    private EditText nameEditText, descriptionEditText, rewardEditText;

    private Date dueDate = null;
    private Coordinates deliveryLocation = null;
    private boolean askingFavor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_favor);

        numGrollies = (TextView) findViewById(R.id.grollies);
        loadGrolliesAmount();

        dueTimeButton = (Button) findViewById(R.id.FechaLimite);

        nameEditText = (EditText) findViewById(R.id.Nombre);
        descriptionEditText = (EditText) findViewById(R.id.DescripcionFavor);
        deliveryLocationButton = (Button) findViewById(R.id.LugarEntrega);
        rewardEditText = (EditText) findViewById(R.id.Recompensa);
    }

    public void chooseDeliveryLocation(View view) {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Clear flags
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(R.layout.dialogmap);

        MapView mapView = (MapView) dialog.findViewById(R.id.mapView);
        MapsInitializer.initialize(this);

        mapView.onCreate(dialog.onSaveInstanceState());
        mapView.onResume();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Coordinates coordinates = LocationService.getInstance().getLocation();
                LatLng position = new LatLng(coordinates.getLatitude(), coordinates.getLongitude());
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                if(ContextCompat.checkSelfPermission(AskFavorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                }

                //Move camera with some delay to avoid bugs
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                         googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                     }
                }, 500);

            }
        });

        Button selectButton = (Button) dialog.findViewById(R.id.selectButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.getMapAsync((googleMap)-> {
                    LatLng center = googleMap.getCameraPosition().target;
                    deliveryLocation = new Coordinates(center.latitude, center.longitude);
                    deliveryLocationButton.setText(LocationService.getInstance().getAddressFromCoordinates(deliveryLocation));
                    dialog.dismiss();
                });
            }
        });

        dialog.show();

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


    public void chooseDueDate(View view) {
        Calendar calendarDate;
        final Calendar currentDate = Calendar.getInstance();
        calendarDate = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendarDate.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(AskFavorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendarDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendarDate.set(Calendar.MINUTE, minute);

                        dueDate = calendarDate.getTime();

                        DateFormat format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        String dateString = format2.format(dueDate);

                        dueTimeButton.setText(dateString);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

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


    public void askFavor(View view) {
        //Comprobar que la informacion del favor es correcta
        //(name, description, deliveryLocation, deliveryDate, reward)

        if(askingFavor) {
            return;
        }

        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String rewardString = rewardEditText.getText().toString();

        if(name.isEmpty() || description.isEmpty() || rewardString.isEmpty() || dueDate == null || deliveryLocation == null) {
            new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Todos los campos tienen que ser rellenados")
                        .setNeutralButton("Ok", (dialog, which) -> {}).show();
            return;
        }

        //Check deliverDate
        if(new Date().getTime() + 3600L * 1000L > dueDate.getTime()) {
            new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("La fecha debe ser de al menos una hora en el futuro")
                        .setNeutralButton("Ok", (dialog, which) -> {}).show();
            return;
        }

        //Check reward
        int reward = Integer.parseInt(rewardString);

        if(reward < 10) {
            new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("La recompensa debe ser al menos de 10 grollies.")
                        .setNeutralButton("Ok", (dialog, which) -> {}).show();
            return;
        }

        Controller controller = Controller.getInstance();
        controller.getCurrentUserGrollies(grollies -> {

            if(grollies < reward) {
                 new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No tienes suficientes grollies.")
                        .setNeutralButton("Ok", (dialog, which) -> {}).show();
                 return;
            }

             new AlertDialog.Builder(this)
                        .setTitle("Confirmación")
                        .setMessage("¿Seguro que quieres crear un favor? Una vez sea asignado a alguien no podrás modificarlo ni borrarlo.")
                        .setNegativeButton("No", (dialog, which) -> { })
                        .setPositiveButton("Sí", (dialog, which) -> {
                            askingFavor = true;

                            controller.createFavor(name, description, dueDate, reward, deliveryLocation, () -> {
                                askingFavor = false;
                                Intent i = new Intent(this, MyFavorsActivity.class);
                                startActivity(i);
                            }, error -> {
                                askingFavor = false;
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

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    public void addPhoto(View view) {
        //Añadir foto
    }

    public void editPhoto(View view) {
        //Añadir foto
    }

}
