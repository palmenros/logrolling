package com.logrolling.client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.logrolling.client.R;
import com.logrolling.client.delegates.ChatDelegate;
import com.logrolling.client.delegates.FavorDelegate;
import com.logrolling.client.delegates.GiftDelegate;
import com.logrolling.client.delegates.UserDelegate;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.services.PermanentStorageService;
import com.logrolling.client.transfer.Filter;
import com.logrolling.client.transfer.TransferCredentials;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.transfer.TransferMessage;
import com.logrolling.client.transfer.TransferPurchase;
import com.logrolling.client.web.WebRequestQueue;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView popUpMessage;
    private ConstraintLayout popUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Initialize services
        WebRequestQueue.createInstance(this);
        PermanentStorageService.createInstance(this);

//        TokenDelegate del = new TokenDelegate();
//
//        del.login(new TransferCredentials("pedro", "false"),
//            (credentials) -> {
//                Toast.makeText(MainActivity.this, credentials.getContent(), Toast.LENGTH_LONG).show();
//            }, (error) -> {
//                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//            }
//        );


//        ChatDelegate del = new ChatDelegate();
//        del.getChatWithUser("pablo",
//                (chat) -> {
//                    Toast.makeText(MainActivity.this, chat.toString(), Toast.LENGTH_LONG).show();
//                },
//                (error) ->{
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                });


//        ChatDelegate del = new ChatDelegate();
//        del.getChatPreviews(
//                (list) -> {
//                    Toast.makeText(MainActivity.this, Arrays.toString(list), Toast.LENGTH_LONG).show();
//                },
//                (error) ->{
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                });


//                ChatDelegate del = new ChatDelegate();
//        del.sendMessage(
//                new TransferMessage("pedro", "juancarlos", "Desde Android"),
//                (error) ->{
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                });

//        FavorDelegate del = new FavorDelegate();
//        del.getAvailableFavorsFiltered(new Filter(10, LocationService.getInstance().getLocation(), 1, 0),
//                (list) -> {
//                    Toast.makeText(MainActivity.this, Arrays.toString(list), Toast.LENGTH_SHORT).show();
//                }, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//        );

//        FavorDelegate del = new FavorDelegate();
//        del.getCreatedFavors(
//                (list) -> {
//                    Toast.makeText(MainActivity.this, Arrays.toString(list), Toast.LENGTH_SHORT).show();
//                }, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//        );

//        FavorDelegate del = new FavorDelegate();
//        del.doFavor(2, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                });

//      FavorDelegate del = new FavorDelegate();
//        del.completeFavor(1, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                });

//        FavorDelegate del = new FavorDelegate();
//        del.getFavorsToBeDone(
//                (list) -> {
//                    Toast.makeText(MainActivity.this, Arrays.toString(list), Toast.LENGTH_SHORT).show();
//                }, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//        );

//        FavorDelegate del = new FavorDelegate();
//        del.createFavor(
//                new TransferFavor(1, "pedro", "Desde Android", "Puedo crear favores desde android", (int)(new Date().getTime() / 1000), 10, LocationService.getInstance().getLocation(), null, false),
//                (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                });

//        FavorDelegate del = new FavorDelegate();
//        del.updateFavor(
//                new TransferFavor(4, "pedro", "Android Actualizado", "Puedo crear favores desde android", (int)(new Date().getTime() / 1000), 10, LocationService.getInstance().getLocation(), null, false),
//                4,
//                (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                });

//        FavorDelegate del = new FavorDelegate();
//        del.deleteFavor(4,
//                (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                });

//        GiftDelegate del = new GiftDelegate();
//        del.getAllGifts((list) -> {
//            Toast.makeText(MainActivity.this, Arrays.toString(list), Toast.LENGTH_SHORT).show();
//        }, (error) -> {
//            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//        });

//                GiftDelegate del = new GiftDelegate();
//        del.purchaseGift( new TransferPurchase("Ordenador", "Android"),
//                () -> {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//                }, (error) -> {
//                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//        );

//        UserDelegate del = new UserDelegate();
//        del.getUserByUsername("pedro", (user) -> {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//        }, (error) -> {
//            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//        });


//        UserDelegate del = new UserDelegate();
//        del.registerUser(new TransferCredentials("raulito", "gafotas"), () -> {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//        }, (error) -> {
//            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//        });

//          UserDelegate del = new UserDelegate();
//          del.updateUser(new TransferCredentials("pedro", "password"), () -> {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//        }, (error) -> {
//            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//        });

        UserDelegate del = new UserDelegate();
          del.deleteUser(() -> {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
        }, (error) -> {
            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
