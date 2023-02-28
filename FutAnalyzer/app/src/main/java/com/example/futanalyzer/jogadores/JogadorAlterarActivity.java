package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.R;

import java.io.IOException;

import modelDominio.Jogador;

public class JogadorAlterarActivity extends AppCompatActivity {
    EditText etNomeAlterarJogadores, etOverallAlterarJogadores;
    Button btSalvarAlterarJogador, btCancelarAlterarJogador;
    int id;

    InformacoesApp informacoesApp;
    Jogador meuJogadorAlterar;
    String msgRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador_alterar);
        etNomeAlterarJogadores = findViewById(R.id.etNomeAlterarJogadores);
        etOverallAlterarJogadores = findViewById(R.id.etOverallAlterarJogadores);
        btSalvarAlterarJogador = findViewById(R.id.btJogadorAltera);
        btCancelarAlterarJogador = findViewById(R.id.btCancelarAlterarJogador);

        informacoesApp = (InformacoesApp)getApplicationContext();

        Intent it = getIntent();

        if(it != null && it.hasExtra("jogador")){
            Jogador meuJogador = (Jogador) it.getSerializableExtra("jogador");

            id = meuJogador.getCod();
            etNomeAlterarJogadores.setText(meuJogador.getNome());
            etOverallAlterarJogadores.setText(String.valueOf(meuJogador.getOverall()));
        }

        btSalvarAlterarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNomeAlterarJogadores.getText().toString().equals("")){
                    if(!etOverallAlterarJogadores.getText().toString().equals("")){
                        String nome = etNomeAlterarJogadores.getText().toString();
                        int over = Integer.parseInt(etOverallAlterarJogadores.getText().toString());
                        meuJogadorAlterar = new Jogador(id, nome, over);
//

//                            criando a thread para o envio do jogador ao servidor
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    //iniciando o protocolo para alterar de jogador
                                    informacoesApp.out.writeObject("JogadorAlterar");
                                    msgRecebida = (String) informacoesApp.in.readObject();
                                    if(msgRecebida.equals("ok")){
                                        informacoesApp.out.writeObject(meuJogadorAlterar);
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        //sincronizando as threads para agir sobre a tela
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                            }
                                        });
                                    }
                                } catch (IOException ioe){
                                    ioe.printStackTrace();
                                } catch (ClassNotFoundException classe){
                                    classe.printStackTrace();
                                }
                            }
                        });
                        thread.start();

                    } else {
                        etOverallAlterarJogadores.setError("Erro: Informe o overall do jogador");
                        etOverallAlterarJogadores.requestFocus();
                    }
                } else{
                    etNomeAlterarJogadores.setError("Erro: Informe o nome do jogador");
                    etNomeAlterarJogadores.requestFocus();
                }
            }
        });

        btCancelarAlterarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}