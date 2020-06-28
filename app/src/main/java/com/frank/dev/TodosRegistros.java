package com.frank.dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.frank.dev.model.MemoriaAtividade;


import java.util.Iterator;
import java.util.List;

public class TodosRegistros extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_registros);
        listView = (ListView) findViewById(R.id.listViewRegistros);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        carregarLista();
    }

    private void carregarLista(){

        List<MemoriaAtividade> memoriasAtividades =  MemoriaAtividade.find(MemoriaAtividade.class,"id>0");

        ListaMemoriasAdapter adapter = new ListaMemoriasAdapter(this, memoriasAtividades);
        listView.setAdapter(adapter);


    }

    public void chamarDetalhes(View view) {
        Intent itt = new Intent(this, DetalhesRegistro.class);
        startActivity(itt);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MemoriaAtividade memoriaAtividade = (MemoriaAtividade) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetalhesRegistro.class);
        intent.putExtra("ID_ATIVIDADE",memoriaAtividade.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       final MemoriaAtividade memoriaAtividade = (MemoriaAtividade) parent.getItemAtPosition(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja excluir a memória?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //remover
                MemoriaAtividade.delete(memoriaAtividade);
                carregarLista();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
    }
}
