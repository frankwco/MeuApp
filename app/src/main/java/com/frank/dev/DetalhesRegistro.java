package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frank.dev.model.MemoriaAtividade;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalhesRegistro extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;
    Double latitude, longitude;
    MemoriaAtividade memoriaAtividade = null;
    private static final String MAP_VIEW_BUNDLE_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_registro);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("ID_ATIVIDADE")) {
            Long id = bundle.getLong("ID_ATIVIDADE");
            memoriaAtividade = MemoriaAtividade.findById(MemoriaAtividade.class, id);

            EditText editText = (EditText) findViewById(R.id.input_titulo);
            editText.setText(memoriaAtividade.getTitulo());

            EditText editTextDescricao = (EditText) findViewById(R.id.input_descricao);
            editTextDescricao.setText(memoriaAtividade.getDescricao());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            if (memoriaAtividade.getImagem1() != null && memoriaAtividade.getImagem1().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(memoriaAtividade.getImagem1(), options);
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
                imageView1.setBackground(null);
                imageView1.setImageBitmap(bitmap);
            }
            if (memoriaAtividade.getImagem2() != null && memoriaAtividade.getImagem2().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(memoriaAtividade.getImagem2(), options);
                ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
                imageView2.setBackground(null);
                imageView2.setImageBitmap(bitmap);
            }
            if (memoriaAtividade.getImagem3() != null && memoriaAtividade.getImagem3().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(memoriaAtividade.getImagem3(), options);
                ImageView imageView3 = (ImageView) findViewById(R.id.imageView4);
                imageView3.setBackground(null);
                imageView3.setImageBitmap(bitmap);
            }

            if (memoriaAtividade.getLongitude() != null && memoriaAtividade.getLongitude() != null) {
                Bundle mapViewBundle = null;
                if (savedInstanceState != null) {
                    mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
                }

                latitude = memoriaAtividade.getLatitude();
                longitude = memoriaAtividade.getLongitude();

                mapView = findViewById(R.id.mapView);
                mapView.onCreate(mapViewBundle);
                mapView.getMapAsync(this);


            }


        }
    }

    public void verImagem(View view) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        ImageView imageView = new ImageView(this);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap bitmap = BitmapFactory.decodeFile(memoriaAtividade.getImagem1(), options);
        imageView.setImageBitmap(bitmap);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        LatLng ny = new LatLng(latitude, longitude);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        gmap.addMarker(markerOptions);


    }
}
