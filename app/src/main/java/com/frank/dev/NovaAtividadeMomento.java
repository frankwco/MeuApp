package com.frank.dev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.frank.dev.model.MemoriaAtividade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NovaAtividadeMomento extends AppCompatActivity {

    private String currentPhotoPath;
    private static final int REQUEST_TAKE_PHOTO = 105;
    private int foto=0;
    private MemoriaAtividade memoriaAtividade = new MemoriaAtividade();
    ImageView imageView1 ;
    ImageView imageView2 ;
    ImageView imageView3 ;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_atividade_momento);
        databaseReference = FirebaseDatabase.getInstance().getReference();

       imageView1 = (ImageView) findViewById(R.id.imageView2);
       imageView2 = (ImageView) findViewById(R.id.imageView3);
       imageView3 = (ImageView) findViewById(R.id.imageView4);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        BottomNavigationView bottomTelaMemoria = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomTelaMemoria.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.menuSalvarMemoria) {
                            salvarAtividadeMemoria();

                            return true;
                        }
                        return false;
                    }
                }
        );

        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                imageView1.setImageBitmap(null);
                imageView1.setBackground(getResources().getDrawable(R.drawable.ic_add_a_photo));
                memoriaAtividade.setImagem1("");
                return true;
            }
        });


    }

    public void chamarCamera1(View view){
        foto=1;
        chamarCamera();
    }
    public void chamarCamera2(View view){
        foto=2;
        chamarCamera();
    }
    public void chamarCamera3(View view){
        foto=3;
        chamarCamera();
    }

    private void chamarCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.frank.dev",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void vozTextoTitulo(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Qual o título?");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Log.e("NovaAtividadeMomento", "Activity não encontrada");
        }
    }

    public void vozTextoDescricao(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Qual a descrição?");
        try {
            startActivityForResult(intent, 101);
        } catch (ActivityNotFoundException a) {
            Log.e("NovaAtividadeMomento", "Activity não encontrada");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText editText = (EditText) findViewById(R.id.input_titulo);
                    editText.setText(resultado.get(0));
                }
                break;
            }
            case 101: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText editText = (EditText) findViewById(R.id.input_descricao);
                    editText.setText(editText.getText().toString() + " " + resultado.get(0));
                }
                break;
            }
            case 105:{
                if(resultCode==RESULT_OK ){
                    try{
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 3;
                        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, options);

                        //Converter o bitmap em Base64 (string), que é útil para mandar a foto para um WS.
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                        byte[] binario = outputStream.toByteArray();
                        String fotoString = Base64.encodeToString(binario, Base64.DEFAULT);

                        Log.i("NovaAtividadeMomento",""+fotoString.length());

                        if(foto==1) {

                            imageView1.setImageBitmap(bitmap);
                            imageView1.setBackground(null);
                            memoriaAtividade.setImagem1(fotoString);
                        }else if(foto==2){

                            imageView2.setImageBitmap(bitmap);
                            imageView2.setBackground(null);
                            memoriaAtividade.setImagem2(fotoString);
                        }else if(foto==2){
                            imageView3.setImageBitmap(bitmap);
                            imageView3.setBackground(null);
                            memoriaAtividade.setImagem3(fotoString);
                        }

                    }catch (Exception i){
                        Log.e("NovaAtividadeMomento","Deu erro!!!");
                    }
                }
                break;
            }
        }
    }

    private void salvarAtividadeMemoria() {
        EditText editTitulo = (EditText) findViewById(R.id.input_titulo);
        EditText editDescricao = (EditText) findViewById(R.id.input_descricao);


        memoriaAtividade.setTitulo(editTitulo.getText().toString());
        memoriaAtividade.setDescricao(editDescricao.getText().toString());

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                memoriaAtividade.setLatitude(location.getLatitude());
                memoriaAtividade.setLongitude(location.getLongitude());
            }
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            memoriaAtividade.setLatitude(location.getLatitude());
            memoriaAtividade.setLongitude(location.getLongitude());
        }
       // Toast.makeText(this, memoriaAtividade.getLatitude()+ " "+memoriaAtividade.getLongitude(), Toast.LENGTH_SHORT).show();

        //memoriaAtividade.save();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String chave = databaseReference.child(user.getUid()).child("memoria").push().getKey();
        memoriaAtividade.setChave(chave);
        databaseReference.child(user.getUid()).child("memoria").child(chave).setValue(memoriaAtividade);

        Toast.makeText(this, "A sua memória foi armazenada com sucesso!!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void testeChamada(View view) {
        Toast.makeText(this, "asdasds2", Toast.LENGTH_SHORT).show();
    }


}
