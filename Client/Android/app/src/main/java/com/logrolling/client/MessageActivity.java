package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {
    private ListView listChat;
    private String[]chats={"Luis","Felipe","Olga","Luis","Felipe","Luis","Felipe","Luis","Felipe","Luis","Felipe",
            "Luis","Felipe","Luis","Felipe","Olga","Luis","Felipe","Luis","Felipe","Luis","Felipe","Luis","Felipe","Luis","Felipe","Luis","Felipe"};
    private TextView numGrollies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        listChat =(ListView)findViewById(R.id.ListaMensajes);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        ArrayAdapter<String> adapter=new ArrayAdapter(this,R.layout.list_favores, chats);
        listChat.setAdapter(adapter);
        listChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MessageActivity.this, ChatPersonActivity.class);
                startActivity(i);
            }
        });
    }

    //Panel Inferior
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }
    public void myFavors(View view) {
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }
    public void favors(View view) {
        Intent i = new Intent(this, FavorsActivity.class);
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
