package com.example.futanalyzer.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futanalyzer.R;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsuario, senhaUsuario;
    Button btEntrarUsuario, btCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsuario = findViewById(R.id.etLoginUsuario);
        senhaUsuario = findViewById(R.id.etSenhaUsuario);
        btEntrarUsuario = findViewById(R.id.btEntrarLogin);
        btCadastrarUsuario = findViewById(R.id.btCadastrarLogin);


    }
}