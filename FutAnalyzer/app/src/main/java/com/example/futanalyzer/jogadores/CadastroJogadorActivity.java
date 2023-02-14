package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futanalyzer.R;
import com.example.futanalyzer.InformacoesApp;

import java.io.IOException;

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
        bCancelar = findViewById(R.id.btCancelarCadastroJogador);

        informacoesApp = (InformacoesApp)getApplicationContext();

        bCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNomeJogador.getText().toString().equals("")){
                    if(!etOverallJogador.getText().toString().equals("")){
                        int overInt = Integer.parseInt(etOverallJogador.getText().toString());
                        if(overInt >= 30 && overInt <= 99){
                            String nome = etNomeJogador.getText().toString();
                            int overall = Integer.parseInt(etOverallJogador.getText().toString());
                            int gol = 0;

                            //criando o objeto da classe
                            meuJogador = new Jogador(nome, overall, gol);
//
//                            informacoesApp.getListaJogadores().add(meuJogador);

//                            Intent it = new Intent(CadastroJogadorActivity.this, JogadoresActivity.class);
//                            startActivity(it);
//                            finish();

//                            criando a thread para o envio do jogador ao servidor
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        //iniciando o protocolo para cadastro de jogador
                                        informacoesApp.out.writeObject("JogadorInserir");
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        if(msgRecebida.equals("ok")){
                                            informacoesApp.out.writeObject(meuJogador);
                                            msgRecebida = (String) informacoesApp.in.readObject();
                                            //sincronizando as threads para agir sobre a tela
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(informacoesApp, "RECEBIDO" + msgRecebida, Toast.LENGTH_SHORT).show();
                                                    limpaCampos();
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

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }

    public void limpaCampos() {
        etNomeJogador.setText("");
        etOverallJogador.setText("");
    }
}