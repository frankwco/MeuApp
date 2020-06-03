package com.frank.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TelaApresenteacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_apresenteacao);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null && bundle.containsKey("totalSoma")){
            TextView textView = (TextView) findViewById(R.id.textResultadoNovaTela);
            textView.setText(bundle.get("totalSoma").toString());
        }

    }
}
