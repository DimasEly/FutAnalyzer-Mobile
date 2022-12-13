package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.futanalyzer.R;
import com.example.futanalyzer.informacoes.InformacoesApp;

import modelDominio.Jogador;

public class CadastroJogadorActivity extends AppCompatActivity {
    EditText etNomeJogador, etOverallJogador;
    Button bCadastrar, bCancelar;

    InformacoesApp informacoesApp;
    Jogador meuJogador;
    String msgRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_jogador);

        etNomeJogador = findViewById(R.id.etNomeCadastroJogadores);
        etOverallJogador = findViewById(R.id.etOverallCadastroJogadores);
        bCadastrar = findViewById(R.id.btJogadorCadastro);
        bCancelar = findViewById(R.id.btCancelarJogadorCadastro);

//        informacoesApp = (InformacoesApp) getApplicationContext();

        bCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNomeJogador.getText().toString().equals("")){
                    if(!etOverallJogador.getText().toString().equals("")){
                        int overInt = Integer.parseInt(etOverallJogador.getText().toString().trim());
                        if(overInt >= 30 && overInt <= 99){
                            Intent it = new Intent(CadastroJogadorActivity.this, JogadoresActivity.class);
                            startActivity(it);
                        } else {
                            etOverallJogador.setError("Erro: overall informado inválido");
                            etOverallJogador.requestFocus();
                        }
                    } else {
                        etOverallJogador.setError("Erro: informe o overall do jogador");
                        etOverallJogador.requestFocus();
                    }
                } else {
                    etNomeJogador.setError("Erro: informe o nome do jogador");
                    etNomeJogador.requestFocus();
                }
            }
        });
    }
}