package com.dlcsistemas.cursoandroid.procampo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dlcsistemas.cursoandroid.procampo.R;
import com.dlcsistemas.cursoandroid.procampo.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void abrirAtividade(View view){
        startActivity(new Intent(this, AtividadeActivity.class));
    }

    public void sairDoApp(View view){
        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        auth.signOut();

        finish();

    }

}
