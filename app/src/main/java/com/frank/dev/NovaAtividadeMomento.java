package com.frank.dev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.frank.dev.model.MemoriaAtividade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class NovaAtividadeMomento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_atividade_momento);

        BottomNavigationView bottomTelaMemoria = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomTelaMemoria.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.menuSalvarMemoria){
                            salvarAtividadeMemoria();
                            return true;
                        }
                        return false;
                    }
                }
        );
    }

    public void vozTextoTitulo(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Qual o título?");
        try{
            startActivityForResult(intent,100);
        }catch (ActivityNotFoundException a){
            Log.e("NovaAtividadeMomento", "Activity não encontrada");
        }
    }

    public void vozTextoDescricao(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Qual a descrição?");
        try{
            startActivityForResult(intent,101);
        }catch (ActivityNotFoundException a){
            Log.e("NovaAtividadeMomento", "Activity não encontrada");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:{
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText editText =(EditText) findViewById(R.id.input_titulo);
                    editText.setText(resultado.get(0));
                }
                break;
            }
            case 101:{
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText editText =(EditText) findViewById(R.id.input_descricao);
                    editText.setText(editText.getText().toString()+" "+resultado.get(0));
                }
                break;
            }
        }
    }

    private void salvarAtividadeMemoria(){
        EditText editTitulo = (EditText) findViewById(R.id.input_titulo);
        EditText editDescricao = (EditText) findViewById(R.id.input_descricao);

        MemoriaAtividade memoriaAtividade = new MemoriaAtividade();
        memoriaAtividade.setTitulo(editTitulo.getText().toString());
        memoriaAtividade.setDescricao(editDescricao.getText().toString());

        memoriaAtividade.save();

        Toast.makeText(this, "A sua memória foi armazenada com sucesso!!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void testeChamada(View view){
        Toast.makeText(this,"asdasds2", Toast.LENGTH_SHORT).show();
    }


}
