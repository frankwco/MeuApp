package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TodosRegistros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_registros);
    }

    public void chamarDetalhes(View view) {
        Intent itt = new Intent(this, DetalhesRegistro.class);
        startActivity(itt);
    }

}
