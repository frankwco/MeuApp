package com.frank.dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frank.dev.model.MemoriaAtividade;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    public void sair(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja sair?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //FirebaseAuth.getInstance().signOut();
            mAuth.signOut();
            finish();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void chamarAddAtividade(View view) {
        Intent itt = new Intent(this, NovaAtividadeMomento.class);
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
