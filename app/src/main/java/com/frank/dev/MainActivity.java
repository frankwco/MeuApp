package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chamarAddAtividade(View view) {
        Intent itt = new Intent(this, Exemplos.class);
        startActivity(itt);
    }

    public void chamarAddLocalizacao(View view) {
        Intent itt = new Intent(this, LocalizacaoAutomatica.class);
        startActivity(itt);
    }

    public void chamarHistorico(View view) {
        Intent itt = new Intent(this, TodosRegistros.class);
        startActivity(itt);
    }

    public void chamarExemplos(View view) {
        Intent itt = new Intent(this, TesteMaterial.class);
        startActivity(itt);
    }

}
