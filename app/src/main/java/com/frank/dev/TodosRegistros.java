package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.frank.dev.model.MemoriaAtividade;


import java.util.Iterator;
import java.util.List;

public class TodosRegistros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_registros);
        carregarLista();
    }

    private void carregarLista(){

        List<MemoriaAtividade> memoriasAtividades =  MemoriaAtividade.find(MemoriaAtividade.class,"id>0");
        ListView listView = (ListView) findViewById(R.id.listViewRegistros);
        ListaMemoriasAdapter adapter = new ListaMemoriasAdapter(this, memoriasAtividades);
        listView.setAdapter(adapter);


    }

    public void chamarDetalhes(View view) {
        Intent itt = new Intent(this, DetalhesRegistro.class);
        startActivity(itt);
    }

}
