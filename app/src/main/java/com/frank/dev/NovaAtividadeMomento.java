package com.frank.dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.frank.dev.model.MemoriaAtividade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

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
