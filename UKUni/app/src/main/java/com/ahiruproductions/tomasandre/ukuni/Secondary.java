package com.ahiruproductions.tomasandre.ukuni;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class Secondary extends AppCompatActivity {
    Intent inte;
    Bundle bundle;
    TextView txtResult;
    Button btnProp;
    int age;

    int[] dataNasc;
    String nome;
    String universidade;
    int ingles;
    int secundario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        inte = getIntent();
        bundle = inte.getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnProp = (Button)findViewById(R.id.btnPropinas);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        dataNasc = bundle.getIntArray(Main.EXT_DATA);
        nome = bundle.getString(Main.EXT_NOME);
        universidade = bundle.getString(Main.EXT_UNIVERSIDADE);
        ingles = bundle.getInt(Main.EXT_NOTA_INGLES);
        secundario = bundle.getInt(Main.EXT_NOTA_SECUNDARIO);

        age = getAge(dataNasc);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "" + age, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (ingles >= 6 && age >= 18 && secundario >= 14){
            txtResult.setText(nome + ", pode candidatar-se á " + universidade + " no Concurso Especial de Acesso e Ingresso do Estudante Internacional.\n \n \n Consulte o valor anual da sua propina.");
        }
        else{
            txtResult.setText(nome + ", não se pode candidatar á " + universidade + " no Concurso Especial de Acesso e Ingresso do Estudante Internacional.");
            btnProp.setVisibility(View.INVISIBLE);
        }
    }

    //-----------------------------------------------------------
    static int getAge(int[] dataNasc)
    {
        int anos = Calendar.getInstance().get(Calendar.YEAR) - dataNasc[2];

        if (Calendar.getInstance().get(Calendar.MONTH) < dataNasc[1] || (Calendar.getInstance().get(Calendar.MONTH) == dataNasc[1] && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < dataNasc[0]))
            anos--;
        return anos;
    }

}
