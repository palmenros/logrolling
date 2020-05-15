package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.logrolling.client.R;

public class RegistrationActivity extends AppCompatActivity {
    private EditText user, password, repPassword;
    private Switch conditions;
    private TextView popUpMessage;
    private ConstraintLayout popUpConfirmation, popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = (EditText) findViewById(R.id.NombreUsuario);
        password = (EditText) findViewById(R.id.Contrasenna);
        repPassword = (EditText) findViewById(R.id.RepContrasenna);
        conditions = (Switch) findViewById(R.id.Condiciones);

        popUpError = (ConstraintLayout) findViewById(R.id.PopUpError10);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage = (TextView) findViewById(R.id.messageError);
        popUpConfirmation = (ConstraintLayout) findViewById(R.id.PopUpConfirm5);
        popUpConfirmation.setVisibility(View.INVISIBLE);

    }

    public void signIn(View view) {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);

    }

    public void register(View view) {
        //Registro

        //showConfirmationPopUp(view) o showErrorPopUp(view) con el error
    }

    public void showPrivacyPolicy(View view) {
        //Mostrar la política de privacidad
    }

    public void showErrorPopUp(View view) {
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }

    public void showConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.VISIBLE);
    }

    public void closeConfirmationPopUp(View view) {
        popUpConfirmation.setVisibility(View.INVISIBLE);
    }
}
