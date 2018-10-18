package com.dlcsistemas.cursoandroid.procampo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dlcsistemas.cursoandroid.procampo.R;

import java.lang.reflect.Array;

public class AtividadeActivity extends AppCompatActivity {

    private Spinner campoAtividades;
    private Spinner campoTalhao;
    private Spinner campoProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade);

        inicializarComponentes();
        carregarDadosSpinner();

    }

    private void carregarDadosSpinner() {
//        String[] atividades = new String[]{
//                "Dessecação",
//                "Plantio"
//        };

        String[] produtos = new String[]{
                "Adubo",
                "Veneno",
                "Semente",
                "Argila"
        };


        ArrayAdapter<String> adapterProdutos = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,produtos);

        adapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        campoProdutos.setAdapter(adapterProdutos);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.atividades,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        campoAtividades.setAdapter(adapter);


        ArrayAdapter adapterTalhao = ArrayAdapter.createFromResource(
                this,
                R.array.talhoes,
                android.R.layout.simple_spinner_item
        );

        adapterTalhao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        campoTalhao.setAdapter(adapterTalhao);

//          MÉTODO USADO NA AULA UDEMY \/

//        String[] atividades = getResources().getStringArray(R.array.atividades);
//
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
//                  this,
//                  android.R.layout.simple_spinner_item,
//                  atividades);
//
//        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        campoAtividades.setAdapter( adaptador );
    }

    private void inicializarComponentes() {

        campoAtividades = findViewById(R.id.spinnerAtividades);
        campoTalhao = findViewById(R.id.spinnerTalhao);
        campoProdutos = findViewById(R.id.spinnerProdutos);

    }

}
