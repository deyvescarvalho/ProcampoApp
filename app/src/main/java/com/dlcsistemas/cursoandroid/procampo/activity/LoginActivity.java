package com.dlcsistemas.cursoandroid.procampo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dlcsistemas.cursoandroid.procampo.Model.Usuario;
import com.dlcsistemas.cursoandroid.procampo.R;
import com.dlcsistemas.cursoandroid.procampo.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText txtEmail, txtSenha;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtSenha = findViewById(R.id.txtSenha);
        txtEmail = findViewById(R.id.txtEmail);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarDados();
            }
        });

    }


    private void ValidarDados() {
        if ( !txtEmail.getText().toString().isEmpty()){
            if ( !txtSenha.getText().toString().isEmpty()){


                    usuario = new Usuario();
                    usuario.setEmail(txtEmail.getText().toString());
                    usuario.setSenha(txtSenha.getText().toString());

                    validarLogin(usuario);

                }else{
                    Toast.makeText(LoginActivity.this, "Preencha a senha", Toast.LENGTH_LONG).show();

                }
            }else{
                Toast.makeText(LoginActivity.this, "Preencha o Email", Toast.LENGTH_LONG).show();

            }
    }

    private void validarLogin(Usuario usuario) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.signInWithEmailAndPassword(this.usuario.getEmail(), this.usuario.getSenha())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(LoginActivity.this, "Usuário logado", Toast.LENGTH_LONG).show();
                               abrirTelaPrincipal();
                           }else{
                               String excecao = "";

                               try  {
                                   throw task.getException();
                               }catch (FirebaseAuthInvalidUserException e){
                                   excecao = "Essa conta de Email, não está cadastrada ou foi desativada";
                               }catch (FirebaseAuthInvalidCredentialsException e){
                                   excecao = "O Email ou senha informados, não estão corretos!";
                               }catch (Exception e){
                                   excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                                   e.printStackTrace();
                               }
                               Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();
                           }
                        }
                    });


    }

    private void abrirTelaPrincipal() {
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}
