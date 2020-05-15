package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.logrolling.client.R;
import com.logrolling.client.delegates.ChatDelegate;
import com.logrolling.client.delegates.FavorDelegate;
import com.logrolling.client.delegates.GiftDelegate;
import com.logrolling.client.delegates.UserDelegate;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.services.PermanentStorageService;
import com.logrolling.client.transfer.Filter;
import com.logrolling.client.transfer.TransferCredentials;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.transfer.TransferMessage;
import com.logrolling.client.transfer.TransferPurchase;
import com.logrolling.client.web.WebRequestQueue;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView popUpMessage;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Initialize services
        WebRequestQueue.createInstance(this);
        PermanentStorageService.createInstance(this);


        //TODO: Use NetworkImageView

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError14);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
    }

    public void advance(View view) {
        //Cuando cargue
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
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
