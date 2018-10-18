package com.dlcsistemas.cursoandroid.procampo.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    //Retorna a instancia do DatabaseReference
    public static DatabaseReference getFirebase(){
        if (firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference().getParent();
        }
        return firebase;
    }

    //Retorna a instancia do firebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){

        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
