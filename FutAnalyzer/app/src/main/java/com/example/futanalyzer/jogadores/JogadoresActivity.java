package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futanalyzer.R;
import com.example.futanalyzer.adapter.ListaJogadoresAdapter;
import com.example.futanalyzer.InformacoesApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Jogador;
import modelDominio.Usuario;

public class JogadoresActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rvJogadores;
    ListaJogadoresAdapter jogadoresAdapter;

    ArrayList<Jogador> listaJogadores;

    InformacoesApp informacoesApp;
    String msgRecebida;

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
                finish();
            }
        });

        carregaListaJogador();
    }

    private void carregaListaJogador() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    informacoesApp.out.writeObject("JogadorLista");
                    Usuario userLogado = informacoesApp.getUsuarioLogado();
                    informacoesApp.out.writeObject(userLogado);
                    listaJogadores = (ArrayList<Jogador>) informacoesApp.in.readObject();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jogadoresAdapter = new ListaJogadoresAdapter(listaJogadores, trataCliqueItem, trataCliqueLongo);
                            rvJogadores.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvJogadores.setItemAnimator(new DefaultItemAnimator());
                            rvJogadores.setAdapter(jogadoresAdapter);
                        }
                    });
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (ClassNotFoundException classe) {
                    classe.printStackTrace();
                }
            }
        });
        thread.start();
    }

    ListaJogadoresAdapter.JogadorOnClickListener trataCliqueItem = new ListaJogadoresAdapter.JogadorOnClickListener() {
            @Override
            public void onJogadorClick(View view, int position) {

                Jogador meuJogador = listaJogadores.get(position);
                Toast.makeText(informacoesApp, "Cod" + listaJogadores.get(position).getCod(), Toast.LENGTH_SHORT).show();
            }
        };

    ListaJogadoresAdapter.JogadorOnLongClickListener trataCliqueLongo = new ListaJogadoresAdapter.JogadorOnLongClickListener(){
        @Override
        public void onJogadorLongClick(View view, int position) {
            Jogador meuJogador = listaJogadores.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(JogadoresActivity.this);
            builder.setTitle("Deseja excluir o jogador?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Thread thread2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                informacoesApp.out.writeObject("JogadorExcluir");
                                msgRecebida = (String) informacoesApp.in.readObject();
                                if(msgRecebida.equals("ok")){
                                    informacoesApp.out.writeObject(meuJogador.getCod());
                                    msgRecebida = (String) informacoesApp.in.readObject();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(informacoesApp, "Jogador excluido", Toast.LENGTH_SHORT).show();
                                            carregaListaJogador();
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
                    thread2.start();

                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    };

    }