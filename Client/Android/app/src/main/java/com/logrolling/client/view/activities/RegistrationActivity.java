package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.data.Settings;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userEditText, passwordEditText, repPasswordEditText;
    private Switch conditionsSwitch;
    private ConstraintLayout popUpConfirmation, popUpError;

    private boolean registering = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userEditText = (EditText) findViewById(R.id.NombreUsuario);
        passwordEditText = (EditText) findViewById(R.id.Contrasenna);
        repPasswordEditText = (EditText) findViewById(R.id.RepContrasenna);
        conditionsSwitch = (Switch) findViewById(R.id.Condiciones);
    }

    public void signIn(View view) {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);

    }

    public void register(View view) {

        if (registering) {
            return;
        }

        //Registro
        String username = userEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmation = repPasswordEditText.getText().toString();

        boolean acceptedConditions = conditionsSwitch.isChecked();

        if (username.isEmpty() || password.isEmpty() || confirmation.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Debes rellenar todos los campos.")
                    .setNeutralButton("Ok", (dialog, which) -> {
                    }).show();
            return;
        }

        if (!acceptedConditions) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Tienes que aceptar las condiciones de uso.")
                    .setNeutralButton("Ok", (dialog, which) -> {
                    }).show();
            return;
        }

        if (!password.equals(confirmation)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Las contraseñas no coinciden.")
                    .setNeutralButton("Ok", (dialog, which) -> {
                    }).show();
            return;
        }

        registering = true;

        Controller.getInstance().registerUser(username, password, () -> {
            registering = false;
            new AlertDialog.Builder(this)
                    .setTitle("Éxito")
                    .setMessage("Tu cuenta se ha registrado correctamente. Introduce ahora tus datos para iniciar sesión.")
                    .setPositiveButton("Inicia sesión", (dialog, which) -> {
                        Intent i = new Intent(this, SignInActivity.class);
                        startActivity(i);
                    }).show();
        }, (error) -> {
            registering = false;
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Error al registrar. Prueba con otro nombre de usuario y vuelve a intentarlo más tarde.")
                    .setNeutralButton("Ok", (dialog, which) -> {
                    }).show();
        });

    }

    public void showPrivacyPolicy(View view) {
        //Mostrar la política de privacidad
        Uri uri = Uri.parse(Settings.getTermsAndConditionsURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
