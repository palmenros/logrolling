package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AskedFavorActivity extends AppCompatActivity {

    private boolean assigned;
    private Button edit, delete;
    private TextView numGrollies;
    private TextView name, description, deliveryLocation, deliveryDate, reward, confirmed, popUpMessage;
    private ImageView photo;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_pedido);

        edit =(Button)findViewById(R.id.editar);
        delete =(Button)findViewById(R.id.borrar);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        name =(TextView)findViewById(R.id.nombre);
        description =(TextView)findViewById(R.id.descrFavor);
        deliveryLocation =(TextView)findViewById(R.id.lugarEntrega);
        deliveryDate =(TextView)findViewById(R.id.fechaLimite);
        reward =(TextView)findViewById(R.id.recompensa);
        photo =(ImageView)findViewById(R.id.foto);

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError2);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);


        confirmed =(TextView)findViewById(R.id.confirmado);

        //Ejemplo
        assigned =true;
        name.setText("Fotocopiar apuntes");
        description.setText("Fotocopiar los apuntes que puedes encontrar en la carpeta de Drive www.drive_ejemplo.es");
        deliveryLocation.setText("Paseo de la Castellana Nº7");
        deliveryDate.setText("Dentro de 1 día y 23 horas");
        reward.setText("7500 grollies");


        if(assigned){
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
            confirmed.setVisibility(View.VISIBLE);
        }else{
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            confirmed.setVisibility(View.INVISIBLE);
        }
    }
    //Botones //Dependiendo de si el favor está assigned hacer una cosa u otra
    public void delete(View view) { //TENDRÁ QUE BORRAR
        Intent i = new Intent(this, MyFavorsActivity.class);
        startActivity(i);
    }
    public void edit(View view) { //TENDRÁ QUE HABILITAR LA OPCION DE EDITAR

    }

    public void chat(View view) { //TENDRÁ QUE LLEVAR A CHAT CON UNA PERSONA DETERMINADA
        Intent i = new Intent(this, ChatPersonActivity.class);
        startActivity(i);
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
    public void gifts(View view) {
        Intent i = new Intent(this, GiftsActivity.class);
        startActivity(i);
    }

    public void buyGrollies(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivity(i);
    }

    //popUpError
    public void showErrorPopUp(View view){
       // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }
    public void closeErrorPopUp(View view){
        popUpError.setVisibility(View.INVISIBLE);
    }
}
