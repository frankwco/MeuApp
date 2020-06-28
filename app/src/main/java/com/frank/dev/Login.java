package com.frank.dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void chamarRecuperarSenha(View view){
        Intent intent = new Intent(this, RecuperarSenha.class);
        startActivity(intent);
    }

    private void chamarPrincipal(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void cadastrar(View view) {
        Intent it = new Intent(this, NovoUsuario.class);
        startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        chamarPrincipal(currentUser);
    }

    public void efetuarLogin(View view) {
        EditText editText1 = (EditText) findViewById(R.id.input_emaill);
        EditText editText2 = (EditText) findViewById(R.id.input_senhaa);
        Log.i("Login", editText1.getText().toString()+" "+editText2.getText().toString());
        mAuth.signInWithEmailAndPassword(editText1.getText().toString(), editText2.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            chamarPrincipal(user);
                        } else {
                            Log.i("Login", task.getResult().toString());
                            Toast.makeText(Login.this, "Deu erro. Tente novamente!!",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
