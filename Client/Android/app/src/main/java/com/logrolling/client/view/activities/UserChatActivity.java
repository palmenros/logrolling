package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.List;

import com.logrolling.client.adapter.ChatMessageAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferMessage;
import com.logrolling.client.web.WebRequestQueue;

public class UserChatActivity extends AppCompatActivity {
    private EditText writeMessage;
    private RecyclerView recycler;
    private TextView name;
    private List<Pair> dataList;
    private ChatMessageAdapter adapter;
    private Runnable updateData;

    private String otherPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_chat);

        Bundle b = getIntent().getExtras();
        otherPerson = b.getString("otherUser");

        NetworkImageView photo = (NetworkImageView) findViewById(R.id.FotoPerfil);
        photo.setImageUrl(
                Controller.getInstance().getUserImageURL(otherPerson),
                WebRequestQueue.getInstance().getImageLoader()
        );

        photo.setClipToOutline(true);

        recycler = (RecyclerView) findViewById(R.id.ListaMensajes);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataList = new ArrayList<Pair>();


        adapter = new ChatMessageAdapter((ArrayList<Pair>) dataList);
        recycler.setAdapter(adapter);

        name = (TextView) findViewById(R.id.NombreChatTitulo);
        writeMessage = (EditText) findViewById(R.id.EscribirMensaje);

        name.setText(otherPerson);


        //Schedule every second to refresh messages

        Handler handler = new Handler();
        updateData = new Runnable() {
            public void run() {
                showMessages();
                handler.postDelayed(updateData, 1000);
            }
        };

        updateData.run();
    }

    public void showMessages() {

        Controller.getInstance().getChatWithUser(otherPerson, transferChat -> {

            if(transferChat.getMessages().size() <= dataList.size()) {
                //Only update if number of messages > number of messages in list to avoid glitches
                return;
            }

            dataList.clear();

            for (TransferMessage message : transferChat.getMessages()) {
                dataList.add(Pair.create(message.getContent(), !message.getFrom().equals(otherPerson)));
            }

            adapter.notifyDataSetChanged();
            recycler.scrollToPosition(dataList.size() - 1);

        }, (error) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Error de red")
                    .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                    .setNeutralButton("Ok", (d, w) -> {
                        //Exit now
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }).show();
        });

    }

    public void sendMessage(View view) {
        String message = writeMessage.getText().toString();
        if (message.length() != 0) {
            //Visually add it
            dataList.add(dataList.size(), Pair.create(message, true));
            adapter.notifyDataSetChanged();

            //Actually send the message to the server
            Controller.getInstance().sendMessage(otherPerson, message, () -> {
            }, (error) -> {
                new AlertDialog.Builder(this)
                        .setTitle("Error de red")
                        .setMessage("No se ha podido conectar con el servidor. Compruebe la conexión e intentelo otra vez.")
                        .setNeutralButton("Ok", (d, w) -> {
                            //Exit now
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }).show();
            });

        }

        recycler.scrollToPosition(dataList.size() - 1);
        writeMessage.setText("");
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

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }
}
