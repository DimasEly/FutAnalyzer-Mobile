package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futanalyzer.R;
import com.example.futanalyzer.adapter.ListaJogadoresAdapter;
import com.example.futanalyzer.InformacoesApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Jogador;

public class JogadoresActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rvJogadores;
    ListaJogadoresAdapter jogadoresAdapter;

    ArrayList<Jogador> listaJogadores;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        fab = findViewById(R.id.fab);
        rvJogadores = findViewById(R.id.rvJogadores);

        informacoesApp = (InformacoesApp) getApplicationContext();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(JogadoresActivity.this, CadastroJogadorActivity.class);
                startActivity(it);
            }
        });

        if (informacoesApp.getListaJogadores() != null) {
            jogadoresAdapter = new ListaJogadoresAdapter(informacoesApp.getListaJogadores(), trataCliqueItem, trataCliqueLongo);
            rvJogadores.setLayoutManager(new LinearLayoutManager(JogadoresActivity.this));
            rvJogadores.setItemAnimator(new DefaultItemAnimator());
            rvJogadores.setAdapter(jogadoresAdapter);
        }

//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        informacoesApp.out.writeObject("listaJogadores");
//                        listaJogadores = (ArrayList<Jogador>) informacoesApp.in.readObject();
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                jogadoresAdapter = new ListaJogadoresAdapter(listaJogadores, trataCliqueItem);
//                                rvJogadores.setLayoutManager(new LinearLayoutManager(informacoesApp));
//                                rvJogadores.setItemAnimator(new DefaultItemAnimator());
//                                rvJogadores.setAdapter(jogadoresAdapter);
//                            }
//                        });
//                    } catch (IOException ioe) {
//                        ioe.printStackTrace();
//                    } catch (ClassNotFoundException classe) {
//                        classe.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
//        }

    }
        ListaJogadoresAdapter.JogadorOnClickListener trataCliqueItem = new ListaJogadoresAdapter.JogadorOnClickListener() {
            @Override
            public void onJogadorClick(View view, int position) {
                Jogador meuJogador = informacoesApp.getListaJogadores().get(position);
            }
        };

    ListaJogadoresAdapter.JogadorOnLongClickListener trataCliqueLongo = new ListaJogadoresAdapter.JogadorOnLongClickListener(){
        @Override
        public void onJogadorLongClick(View view, int position) {
            
        }
    };

    }