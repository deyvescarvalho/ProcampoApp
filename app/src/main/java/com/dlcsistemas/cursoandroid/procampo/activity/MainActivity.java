package com.dlcsistemas.cursoandroid.procampo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dlcsistemas.cursoandroid.procampo.R;
import com.dlcsistemas.cursoandroid.procampo.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {





    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);













        /*Deslogar usuario*/

//        autenticacao.signOut();



//        autenticacao.createUserWithEmailAndPassword("deyvescarvalho2@gmail.com", "123456")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Log.i("Retorno", "sUCESSO CADASTRADO");
//                        }else{
//                            Log.i("Retorno", "Eroo");
//
//                        }
//                    }
//                });

//        DatabaseReference usuarios = referencia.child("usuarios");
//        DatabaseReference produtos = referencia.child("produtos");

//        usuarios.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.i("Firebase", dataSnapshot.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        Cadastrando usuario
        /*Usuario user = new Usuario();

        user.setNome("Guilhermo");
        user.setSobrenome("Furica");
        user.setIdade(20);

        try {
            usuarios.push().setValue(user);
            Log.i("Cadastrando", "Cadastrado com sucesso");
        }catch (Exception e){

            Log.i("Cadastrando", e.getMessage());
        }*/


//        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Deyves");
//        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(3);
//        try {


            /*Pesquisa de usuarios*/

//        DatabaseReference usuarioPesquisa = usuarios.child("-LLgdtz0vvku9bdHBojl");

//            usuarioPesquisa.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Usuario usuarioDados = dataSnapshot.getValue(Usuario.class);

//                Log.i("Dados do usuario", " Nome: " + usuarioDados.getNome());
//                    Log.i("Dados do usuer", dataSnapshot.getValue().toString());
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });

//        }catch (Exception e){
//            e.getMessage();
//        }







    }

    protected void onStart(){
        super.onStart();
        verificarUsuarioLogado();

    }




    public void Cadastrar(View view){

        startActivity(new Intent(this, CadastroActivity.class));

    }

    public void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() != null){
            startActivity(new Intent(this, PrincipalActivity.class));
            finish();
        }

    }

    public void Entrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }



//        @Override
//        public void onStart() {
//            super.onStart();
//            autenticacao.signOut();
//            Toast.makeText(MainActivity.this, "Usuário foi deslogado", Toast.LENGTH_LONG).show();
//        }


//    public void LogarUsuario(EditText email, EditText senha) {
//        Log.i("Status", "Entrou no método" + "Email> " + email.getText().toString() + "  Senha: " + senha.getText().toString() + "  ");
//        /* verificar se está logado */
//        if (autenticacao.getCurrentUser() != null){
//            Log.i("Usuario logado", "logado");
//        }else{
//            /*Logar usuario*/
//            autenticacao.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()){
//                                Log.i("Logado", "Logou com sucesso");
//                                Toast.makeText(MainActivity.this, "Olá! Você está logado!",Toast.LENGTH_LONG).show();
//                            }else{
//                                Log.i("Logado", "Erro ao Logar");
//                            }
//                        }
//                    });
//        }
//    }


    public void Logar(View view){

    }
}
