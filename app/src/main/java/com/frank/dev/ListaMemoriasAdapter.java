package com.frank.dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frank.dev.model.MemoriaAtividade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaMemoriasAdapter extends BaseAdapter {

    Context context;
    List<MemoriaAtividade> memorias;

    public ListaMemoriasAdapter(Context context, List<MemoriaAtividade> memorias) {
        this.context = context;
        this.memorias = memorias;
    }

    @Override
    public int getCount() {
        return memorias.size();
    }

    @Override
    public Object getItem(int position) {
        return memorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return memorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewLinha = LayoutInflater.from(context).inflate(R.layout.linha_memoria,   parent, false);
        MemoriaAtividade memoriaAtividade = memorias.get(position);
        TextView textView = viewLinha.findViewById(R.id.textTituloLinha);
        textView.setText(memoriaAtividade.getTitulo());

        TextView textViewData = viewLinha.findViewById(R.id.textDataMemoria);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        textViewData.setText(format.format(memoriaAtividade.getDataMemoria()));

        return viewLinha;
    }
}
