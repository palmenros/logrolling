package com.logrolling.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private EditText user, password;
    private TextView popUpMessage;
    private ConstraintLayout popUpError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        user =(EditText)findViewById(R.id.NombreUsuario);
        password =(EditText)findViewById(R.id.Contrasenna);

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError5);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);
    }
    public void registration(View view){
        Intent i=new Intent(this, RegistrationAvtivity.class);
        startActivity(i);
    }
    public void signIn(View view){
        if(true ){//Habrá que comprobar que el user esta registrado
            Intent i=new Intent(this, FavorsActivity.class);
            startActivity(i);
        }
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
