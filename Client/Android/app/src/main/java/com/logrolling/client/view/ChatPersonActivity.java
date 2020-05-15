package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.List;

import com.logrolling.client.adapter.DataAdapter;

public class ChatPersonActivity extends AppCompatActivity {
    private EditText writeMessage;
    private RecyclerView recycler;
    private TextView name;
    private List<Pair> dataList;
    private DataAdapter adapter;

    private ConstraintLayout popUpError;
    private TextView popUpMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_persona);

        recycler = (RecyclerView) findViewById(R.id.ListaMensajes);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataList = new ArrayList<Pair>();
        for (int i = 0; i < 2; i++) {
            dataList.add(i, Pair.create("Mensaje " + i, i % 2 == 0 ? true : false));
        }
        adapter = new DataAdapter((ArrayList<Pair>) dataList);
        recycler.setAdapter(adapter);

        name = (TextView) findViewById(R.id.NombreChatTitulo);
        writeMessage = (EditText) findViewById(R.id.EscribirMensaje);

        recycler.scrollToPosition(dataList.size() - 1);

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError11);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
    }

    public void write(View view) {
        String mensaje = writeMessage.getText().toString();
        if (mensaje.length() != 0) {
            dataList.add(dataList.size(), Pair.create(mensaje, true));
            adapter.notifyDataSetChanged();
        }

        recycler.scrollToPosition(dataList.size() - 1);
        writeMessage.setText("");
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

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }

    public void showErrorPopUp(View view) {
        popUpError.setVisibility(View.VISIBLE);
    }
}
