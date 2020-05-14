package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logrolling.client.R;

public class MainActivity extends AppCompatActivity {
    private TextView popUpMessage;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Load Request Queue here

        //TODO: Use NetworkImageView

        popUpError=(ConstraintLayout)findViewById(R.id.PopUpError14);
        popUpError.setVisibility(View.INVISIBLE);
        popUpMessage=(TextView)findViewById(R.id.messageError);
    }

    public void advance(View view){
        //Cuando cargue
        Intent i=new Intent(this, SignInActivity.class);
        startActivity(i);
    }

    //popUpError
    public void showErrorPopUp(View view) {
        // popUpMessage.setText();
        popUpError.setVisibility(View.VISIBLE);
    }

    public void closeErrorPopUp(View view) {
        popUpError.setVisibility(View.INVISIBLE);
    }
}
