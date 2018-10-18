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
import com.dlcsistemas.cursoandroid.procampo.helper.Base64Custom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText txtNome, txtEmail, txtSenha;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtNome = findViewById(R.id.textNome);
        txtEmail = findViewById(R.id.textEmail);
        txtSenha = findViewById(R.id.textSenha);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarDados(txtNome, txtEmail, txtSenha);
            }
        });
    }

    private void ValidarDados(EditText txtNome, EditText txtEmail, EditText txtSenha) {

        if (!txtNome.getText().toString().isEmpty()){
            if ( !txtEmail.getText().toString().isEmpty()){
                if ( !txtSenha.getText().toString().isEmpty()){


                    usuario = new Usuario();
                    usuario.setNome(txtNome.getText().toString());
                    usuario.setEmail(txtEmail.getText().toString());
                    usuario.setSenha(txtSenha.getText().toString());

                        cadastrarUsuario();

                }else{
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_LONG).show();

                }
            }else{
                Toast.makeText(this, "Preencha o Email", Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_LONG).show();
        }
    }

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
//                    Toast.makeText(CadastroActivity.this, "Usário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

//                    startActivity(new Intent(CadastroActivity.this, PrincipalActivity.class));

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());

                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();

                    finish();

                }else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um Email válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esse Email já foi cadastrado!";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
