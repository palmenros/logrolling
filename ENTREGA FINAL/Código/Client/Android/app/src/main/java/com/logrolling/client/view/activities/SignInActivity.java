package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.logrolling.client.R;
import com.logrolling.client.services.AuthenticationService;

public class SignInActivity extends AppCompatActivity {
    private EditText userEditText, passwordEditText;

    private boolean signingIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEditText = (EditText) findViewById(R.id.NombreUsuario);
        passwordEditText = (EditText) findViewById(R.id.Contrasenna);
    }

    public void registration(View view) {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }

    public void signIn(View view) {

        if (signingIn) {
            return;
        }

        signingIn = true;

        String userName = userEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        AuthenticationService.getInstance().tryLogWithCredentials(userName, password,
                () -> {
                    signingIn = false;
                    Intent i = new Intent(this, SearchActivity.class);
                    startActivity(i);
                },
                (error) -> {
                    signingIn = false;
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Error al iniciar sesión.")
                            .setNeutralButton("Ok", (dialog, which) -> {

                            }).show();
                });
    }


}
