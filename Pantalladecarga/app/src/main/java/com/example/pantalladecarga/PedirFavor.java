package com.example.pantalladecarga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PedirFavor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);

    }

    public void pedirFavor(View view){
        //pedir favor
        Intent i=new Intent(this,Favores.class);
        startActivity(i);
    }

}
