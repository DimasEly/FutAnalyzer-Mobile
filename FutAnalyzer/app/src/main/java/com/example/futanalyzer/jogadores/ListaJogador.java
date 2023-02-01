package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.R;
import com.example.futanalyzer.adapter.ListaJogadoresAdapter;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Jogador;

public class ListaJogador extends AppCompatActivity {
    RecyclerView rvListaObjetoRecycler;
//    ListaJogadoresAdapter jogadorAdapter;
//
//    ArrayList<Jogador> listaJogadores;
//
//    InformacoesApp informacoesApp;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lista_jogador);
//        rvListaObjetoRecycler = findViewById(R.id.rvJogadoresCadastrados);
//
//        informacoesApp = (InformacoesApp) getApplicationContext();
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    informacoesApp.out.writeObject("listaProdutos");
//                    listaJogadores = (ArrayList<Jogador>) informacoesApp.in.readObject();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            jogadorAdapter = new ListaJogadoresAdapter(listaJogadores, trataCliqueItem);
//                            rvListaObjetoRecycler.setLayoutManager(new LinearLayoutManager(informacoesApp));
//                            rvListaObjetoRecycler.setItemAnimator(new DefaultItemAnimator());
//                            rvListaObjetoRecycler.setAdapter(jogadorAdapter);
//                        }
//                    });
//                } catch (IOException ioe){
//                    ioe.printStackTrace();
//                } catch (ClassNotFoundException classe){
//                    classe.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
//    }
//
//    ListaJogadoresAdapter.JogadorOnClickListener trataCliqueItem = new ListaJogadoresAdapter.JogadorOnClickListener() {
//        @Override
//        public void onJogadorClick(View view, int position) {
//            Jogador meuJogador = listaJogadores.get(position);
//
//            Toast.makeText(informacoesApp, "Nome" + meuJogador.getNome(), Toast.LENGTH_SHORT).show();
//        }
//    };
}