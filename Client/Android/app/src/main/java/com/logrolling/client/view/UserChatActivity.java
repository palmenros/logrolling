package com.logrolling.client.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.logrolling.client.R;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

import com.logrolling.client.adapter.ChatMessageAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferMessage;

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

        Bundle b = getIntent().getExtras();
        otherPerson = b.getString("otherUser");

        //TODO: Customize image

        setContentView(R.layout.activity_user_chat);

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
        updateData = new Runnable(){
            public void run(){
                showMessages();
                handler.postDelayed(updateData,1000);
            }
        };

        updateData.run();
    }

    public void showMessages() {

        Controller.getInstance().getChatWithUser(otherPerson, transferChat -> {

            dataList.clear();

            for(TransferMessage message : transferChat.getMessages()) {
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
}
