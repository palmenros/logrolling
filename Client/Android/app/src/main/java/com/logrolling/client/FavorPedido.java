package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FavorPedido extends AppCompatActivity {

    private boolean adjudicado;
    private Button editar, borrar;
    private TextView numGrollies;
    private TextView nombre, descripcion, lugarEntrega, fechaEntrega, recompensa, confirmado;
    private ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_pedido);

        editar=(Button)findViewById(R.id.editar);
        borrar=(Button)findViewById(R.id.borrar);

        numGrollies=(TextView)findViewById(R.id.grollies);
        numGrollies.setText("");//Pedir el número de grollies a quien sea

        nombre=(TextView)findViewById(R.id.nombre);
        descripcion=(TextView)findViewById(R.id.descrFavor);
        lugarEntrega=(TextView)findViewById(R.id.lugarEntrega);
        fechaEntrega=(TextView)findViewById(R.id.fechaLimite);
        recompensa=(TextView)findViewById(R.id.recompensa);
        foto=(ImageView)findViewById(R.id.foto);

        confirmado=(TextView)findViewById(R.id.confirmado);

        //Ejemplo
        adjudicado =true;
        nombre.setText("Fotocopiar apuntes");
        descripcion.setText("Fotocopiar los apuntes que puedes encontrar en la carpeta de Drive www.drive_ejemplo.es");
        lugarEntrega.setText("Paseo de la Castellana Nº7");
        fechaEntrega.setText("Dentro de 1 día y 23 horas");
        recompensa.setText("7500 grollies");


        if(adjudicado){
            editar.setVisibility(View.INVISIBLE);
            borrar.setVisibility(View.INVISIBLE);
            confirmado.setVisibility(View.VISIBLE);
        }else{
            editar.setVisibility(View.VISIBLE);
            borrar.setVisibility(View.VISIBLE);
            confirmado.setVisibility(View.INVISIBLE);
        }
    }
    //Botones //Dependiendo de si el favor está adjudicado hacer una cosa u otra
    public void borrar(View view) { //TENDRÁ QUE BORRAR
        Intent i = new Intent(this, MisFavores.class);
        startActivity(i);
    }
    public void editar(View view) { //TENDRÁ QUE HABILITAR LA OPCION DE EDITAR

    }

    public void chat(View view) { //TENDRÁ QUE LLEVAR A CHAT CON UNA PERSONA DETERMINADA
        Intent i = new Intent(this, ChatPersona.class);
        startActivity(i);
    }


    //Panel Inferior
    public void favores(View view) {
        Intent i = new Intent(this, Favores.class);
        startActivity(i);
    }
    public void misFavores(View view) {
        Intent i = new Intent(this, MisFavores.class);
        startActivity(i);
    }
    public void mensajes(View view) {
        Intent i = new Intent(this, Mensajes.class);
        startActivity(i);

    }
    public void configuracion(View view) {
        Intent i = new Intent(this, Configuracion.class);
        startActivity(i);
    }
    public void regalos(View view) {
        Intent i = new Intent(this, Regalos.class);
        startActivity(i);
    }

    public void comprarGrollies(View view) {
        Intent i = new Intent(this, Tienda.class);
        startActivity(i);
    }
}
