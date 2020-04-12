package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GiftsActivity extends AppCompatActivity {
    private ListView listFavors;
    private TextView numGrollies;
    private String[] gifts ={"Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox",
            "Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo","Play","Xbox","Tarjeta regalo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regalos);
        listFavors =(ListView)findViewById(R.id.ListaMensajes);


        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea


        ArrayAdapter<String> adapter=new ArrayAdapter(this,R.layout.list_regalos, gifts);
        listFavors.setAdapter(adapter);
        listFavors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
            }
        });
    }

    //Panel Inferior
    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
        startActivity(i);
    }
    public void myFavors(View view) {
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



    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }


}
