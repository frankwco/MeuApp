package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exemplos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Exemplos","Estamos no on Start");
    }

    public void resultadoSegundaTela(View view){
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("totalSoma",resultado());
        //startActivity(intent);
        finish();
    }

    private Double resultado(){
        EditText campo1 = (EditText) findViewById(R.id.textValor1);
        EditText campo2 = (EditText) findViewById(R.id.textValor2);

        Double valor1 = Double.parseDouble(campo1.getText().toString());
        Double valor2 = Double.parseDouble(campo2.getText().toString());
        Double resultado = valor1 + valor2;
        return resultado;
    }



    public void somar(View view){

        Double resultado = resultado();

        Toast.makeText(this, "Resultado da soma: "+resultado(), Toast.LENGTH_LONG).show();

        TextView textView = (TextView) findViewById(R.id.textResultadoNovaTela2);
        textView.setText("Total: "+resultado);

    }
}
