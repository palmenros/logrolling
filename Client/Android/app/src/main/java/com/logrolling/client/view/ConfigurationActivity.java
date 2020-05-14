package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;

public class ConfigurationActivity extends AppCompatActivity {
    private ConstraintLayout popUpSignOut;
    private TextView numGrollies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        popUpSignOut=(ConstraintLayout)findViewById(R.id.PopUpSignOut);
        popUpSignOut.setVisibility(View.INVISIBLE);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea
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

    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
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
        showSignOutConfirm(view);
    }
    public void showSignOutConfirm(View view){
        popUpSignOut.setVisibility(View.VISIBLE);
    }
    public void closeSignOutConfirm(View view){
        popUpSignOut.setVisibility(View.INVISIBLE);
    }
    public void signOutConfirmed(View view) {
        closeSignOutConfirm(view);
    }
}
