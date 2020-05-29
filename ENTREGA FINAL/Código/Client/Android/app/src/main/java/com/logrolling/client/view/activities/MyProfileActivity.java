package com.logrolling.client.view.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.logrolling.client.R;
import com.logrolling.client.controllers.Controller;
import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.web.WebRequestQueue;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

public class MyProfileActivity extends AppCompatActivity {
    private EditText userEditText, passwordEditText, repPasswordEditText;
    private TextView numGrollies;
    private NetworkImageView imageView;

    private boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        userEditText = (EditText) findViewById(R.id.nombreUsuario);
        passwordEditText = (EditText) findViewById(R.id.contrasenna);
        repPasswordEditText = (EditText) findViewById(R.id.repContrasenna);
        numGrollies = (TextView) findViewById(R.id.grollies);

        imageView = (NetworkImageView) findViewById(R.id.imagenPerfil);
        imageView.setClipToOutline(true);

        WebRequestQueue.getInstance().getImageCache().evictAll();

        Controller.getInstance().getCurrentUser((user) -> {
            numGrollies.setText(Integer.valueOf(user.getGrollies()).toString());
            userEditText.setText(user.getUsername());
            imageView.setImageUrl(
                    Controller.getInstance().getUserImageURL(user.getUsername()),
                    WebRequestQueue.getInstance().getImageLoader()
            );
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

        if (updating) {
            return;
        }

        if (password.isEmpty() || confirmation.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Debes rellenar todos los campos.")
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

    public void changeProfilePicture(View view) {
        //Ask for a square photo
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .setFixAspectRatio(true)
                .setMinCropResultSize(200, 200)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 700, 700, false);

                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Subiendo imagen...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    Controller.getInstance().uploadUserImage(scaled, () -> {
                        progressDialog.dismiss();
                        //Success

                        //Change profile photo
                        imageView.setImageBitmap(scaled);

                        new AlertDialog.Builder(this)
                                .setTitle("Éxito")
                                .setMessage("Éxito al cambiar la foto de perfil. El cambio se mostrará en unos segundos.")
                                .setNeutralButton("Ok", (dialog, which) -> {
                                }).show();

                    }, (error) -> {
                        progressDialog.dismiss();

                        new AlertDialog.Builder(this)
                                .setTitle("Error")
                                .setMessage("Error al cambiar la foto de perfil. Inténtalo en unos instantes.")
                                .setNeutralButton("Ok", (dialog, which) -> {
                                }).show();
                    });

                } catch (IOException e) {
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Error al cambiar la foto de perfil")
                            .setNeutralButton("Ok", (dialog, which) -> {
                            }).show();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Error al cambiar la foto de perfil")
                        .setNeutralButton("Ok", (dialog, which) -> {
                        }).show();

            }
        }
    }
}
