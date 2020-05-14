package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;

import java.util.ArrayList;

import com.logrolling.client.adapter.AdapterRegalos;
import com.logrolling.client.transfer.Gift;

public class GiftsActivity extends AppCompatActivity {
    private RecyclerView listFavors;
    private TextView numGrollies;
    private ArrayList<Gift> gifts = new ArrayList<Gift>();
    private TextView popUpMessage;
    private ConstraintLayout popUpConfirmation,popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regalos);
        listFavors =(RecyclerView)findViewById(R.id.ListaRegalos);
        listFavors.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError8);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);
        popUpConfirmation=(ConstraintLayout)findViewById(R.id.PopUpConfirm2);
        popUpConfirmation.setVisibility(View.INVISIBLE);

        llenarLista();

        AdapterRegalos adapter=new AdapterRegalos(gifts);
        listFavors.setAdapter(adapter);
        /*listFavors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showConfirmationPopUp(view);
            }
        });*/
    }
    private void llenarLista(){
        for(int i=0;i<10;i++) {
            gifts.add(new Gift("Regalo "+i,i*1000,R.drawable.ic_card_giftcard_black_24dp));
        }
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



    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }
    public void showConfirmationPopUp(View view){
        popUpConfirmation.setVisibility(View.VISIBLE);
    }
    public void closeConfirmationPopUp(View view){  popUpConfirmation.setVisibility(View.INVISIBLE);}
    public void showErrorPopUp(View view){
        popUpConfirmation.setVisibility(View.VISIBLE);
    }
}
