package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NovaAtividadeMomento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_atividade_momento);
    }

    public void testeChamada(View view){
        Toast.makeText(this,"asdasd", Toast.LENGTH_SHORT).show();
    }
}
