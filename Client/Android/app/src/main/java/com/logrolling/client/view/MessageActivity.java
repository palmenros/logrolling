package com.logrolling.client.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;
import java.util.Collections;

import com.logrolling.client.adapter.MessagePreviewAdapter;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.transfer.TransferMessagePreview;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView messagesPreviewRecyclerView;
    private ArrayList<TransferMessagePreview> chats = new ArrayList<TransferMessagePreview>();
    private TextView numGrollies;
    private MessagePreviewAdapter adapter;
    private TextView emptyMessagesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        numGrollies = (TextView) findViewById(R.id.grollies);
        emptyMessagesTextView = (TextView) findViewById(R.id.textView2);
        loadGrolliesAmount();

        messagesPreviewRecyclerView = (RecyclerView) findViewById(R.id.ListaMensajes);
        messagesPreviewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        messagesPreviewRecyclerView.setVisibility(View.VISIBLE);

        //Divider between messages
        RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {
            private Drawable mDivider = getResources().getDrawable(R.drawable.message_separator_line);

            @Override
            public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                int dividerLeft = parent.getPaddingLeft();
                int dividerRight = parent.getWidth() - parent.getPaddingRight();

                int childCount = parent.getChildCount();
                for (int i = 0; i <= childCount - 2; i++) {
                    View child = parent.getChildAt(i);

                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                    int dividerTop = child.getBottom() + params.bottomMargin;
                    int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                    mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                    mDivider.draw(canvas);
                }
            }
        };


        messagesPreviewRecyclerView.addItemDecoration(decoration);

        fillMessagePreviews();

        /*listChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que sea
                Intent i = new Intent(MessageActivity.this, MessageActivity.class);
                startActivity(i);
            }
        });*/
    }

    private void fillMessagePreviews() {

        Controller.getInstance().getChatPreviews((messagePreviews) -> {

            Collections.addAll(chats, messagePreviews);
            adapter = new MessagePreviewAdapter(chats, (view) -> {
                int position = messagesPreviewRecyclerView.getChildLayoutPosition(view);
                TransferMessagePreview messagePreview = chats.get(position);
                Intent i = new Intent(this, UserChatActivity.class);
                i.putExtra("otherUser", messagePreview.getUser());
                startActivity(i);
            });
            messagesPreviewRecyclerView.setAdapter(adapter);

            if(messagePreviews.length == 0) {
                showEmptyMessages();
            }

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

    private void showEmptyMessages() {
        emptyMessagesTextView.setVisibility(View.VISIBLE);
        messagesPreviewRecyclerView.setVisibility(View.GONE);
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

    //Panel Inferior
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }

    public void favors(View view) {
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }

    public void search(View view) {
        Intent i = new Intent(this, SearchActivity.class);
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
