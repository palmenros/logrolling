package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.logrolling.client.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AskFavorActivity extends AppCompatActivity {
    private TextView numGrollies;
    private TextView popUpMessage;

    private Button dueTimeButton;

    private EditText name, description, deliveryLocation, reward;
    private ConstraintLayout popUpConfirmation, popUpError;

    private Date dueDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);

        numGrollies = (TextView) findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError9);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
        popUpConfirmation = (ConstraintLayout) findViewById(R.id.PopUpConfirm4);
        popUpConfirmation.setVisibility(View.INVISIBLE);

        dueTimeButton = (Button) findViewById(R.id.FechaLimite);

        name = (EditText) findViewById(R.id.Nombre);
        description = (EditText) findViewById(R.id.DescripcionFavor);
        deliveryLocation = (EditText) findViewById(R.id.LugarEntrega);
        reward = (EditText) findViewById(R.id.Recompensa);
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

    public void configuration(View view) {
        Intent i = new Intent(this, ConfigurationActivity.class);
        startActivity(i);
    }

    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }


    public void askFavor(View view) {
        //Comprobar que la informacion del favor es correcta
        //(name, description, deliveryLocation, deliveryDate, reward)
        showConfirmationPopUp(view);
    }

    public void askFavorConfirmed(View view) {
        //pedirFavor
        closeConfirmationPopUp(view);
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
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

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }

    public void showConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.VISIBLE);
    }

    public void closeConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.INVISIBLE);
    }

    public void showErrorPopUp(View view) {
        popUpError.setVisibility(View.VISIBLE);
    }
}
