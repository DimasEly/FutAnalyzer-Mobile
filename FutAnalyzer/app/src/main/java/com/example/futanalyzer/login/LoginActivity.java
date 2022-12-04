package com.example.futanalyzer.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futanalyzer.MainActivity;
import com.example.futanalyzer.R;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginUsuario, etSenhaUsuario;
    Button btEntrarUsuario, btCadastrarUsuario;
    TextView tvEsqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUsuario = findViewById(R.id.etLoginUsuario);
        etSenhaUsuario = findViewById(R.id.etSenhaUsuario);
        btEntrarUsuario = findViewById(R.id.btEntrarLogin);
        btCadastrarUsuario = findViewById(R.id.btCadastrarLogin);
        tvEsqueceuSenha = findViewById(R.id.tvEsqueceuSenha);


        btEntrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etLoginUsuario.getText().toString().equals("")){
                    if(!etSenhaUsuario.getText().toString().equals("")){
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                    } else {
                        etSenhaUsuario.setError("Erro: Informe a senha");
                        etSenhaUsuario.requestFocus();
                    }
                } else {
                    etLoginUsuario.setError("Erro: Informe o login");
                    etLoginUsuario.requestFocus();
                }
            }
        });

        btCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        tvEsqueceuSenha.setPaintFlags(tvEsqueceuSenha.getPaintFlags());
        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });
    }
}