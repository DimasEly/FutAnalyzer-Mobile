package com.example.futanalyzer.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.futanalyzer.R;

public class CadastroActivity extends AppCompatActivity {
    EditText etUsuarioCadastro, etEmailCadastro, etSenhaCadastro;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        etUsuarioCadastro = findViewById(R.id.etUsuarioCadastro);
        etEmailCadastro = findViewById(R.id.etEmailCadastro);
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro);
        btCadastrar = findViewById(R.id.btCadastrarCadastro);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etUsuarioCadastro.getText().toString().equals("")){
                    if(!etEmailCadastro.getText().toString().equals("")){
                        if(!etSenhaCadastro.getText().toString().equals("")){
                            Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                            startActivity(it);
                        } else {
                            etSenhaCadastro.setError("Erro: Informe a senha");
                            etSenhaCadastro.requestFocus();
                        }
                    } else{
                        etEmailCadastro.setError("Erro: Informe o email");
                        etEmailCadastro.requestFocus();
                    }
                } else {
                    etUsuarioCadastro.setError("Erro: Informe o usuário");
                    etUsuarioCadastro.requestFocus();
                }
            }
        });

    }
}