package com.logrolling.client.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;

public class MyProfileActivity extends AppCompatActivity {
    private EditText userEditText, passwordEditText, repPasswordEditText;
    private TextView numGrollies;

    private boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        userEditText = (EditText) findViewById(R.id.nombreUsuario);
        passwordEditText = (EditText) findViewById(R.id.contrasenna);
        repPasswordEditText = (EditText) findViewById(R.id.repContrasenna);
        numGrollies = (TextView) findViewById(R.id.grollies);

        Controller.getInstance().getCurrentUser((user) -> {
            numGrollies.setText( Integer.valueOf(user.getGrollies()).toString() );
            userEditText.setText(user.getUsername());
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


    public void guardarCambios(View view) {

        String password = passwordEditText.getText().toString();
        String confirmation = repPasswordEditText.getText().toString();

        if(updating) {
            return;
        }

        if(password.isEmpty() || confirmation.isEmpty()) {
            new AlertDialog.Builder(this)
                           .setTitle("Error")
                           .setMessage("Debes rellenar todos los campos.")
                           .setNeutralButton("Ok", (dialog, which) -> {}).show();
            return;
        }

        if(!password.equals(confirmation)) {
            new AlertDialog.Builder(this)
                           .setTitle("Error")
                           .setMessage("Las contraseñas no coinciden.")
                           .setNeutralButton("Ok", (dialog, which) -> {}).show();
            return;
        }

        updating = true;

        Controller.getInstance().updatePassword(password, () -> {
            updating = false;
            new AlertDialog.Builder(this)
                    .setTitle("Éxito")
                    .setMessage("Tu perfil ha sido actualizado")
                    .setNeutralButton("Ok", (dialog, which) -> {
                        Intent i = new Intent(this, ConfigurationActivity.class);
                        startActivity(i);
                    }).show();

        }, (error) -> {
            updating = false;
            new AlertDialog.Builder(this)
                           .setTitle("Error")
                           .setMessage("Error al actualizar la contraseña")
                           .setNeutralButton("Ok", (dialog, which) -> {
                           }).show();
        });

    }

    public void cambiarFoto(View view) {

    }

}
