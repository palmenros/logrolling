package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.logrolling.client.R;
import com.logrolling.client.exceptions.RequestException;
import com.logrolling.client.web.WebRequestQueue;
import com.logrolling.client.web.WebServiceClient;

public class MainActivity extends AppCompatActivity {
    private TextView popUpMessage;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Load Request Queue here
        WebRequestQueue.createInstance(this);

        WebServiceClient client = new WebServiceClient();

        client.getRequest("http://192.168.0.100:8080/Server_war_exploded/favors", null, new WebServiceClient.ResponseListener<String>() {
            @Override
            public void onResponse(String str) {
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
            }
        }, null, new WebServiceClient.ErrorListener() {
            @Override
            public void onError(RequestException ex) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });


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
