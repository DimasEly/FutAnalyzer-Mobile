package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futanalyzer.R;
import com.example.futanalyzer.adapter.ListaJogadoresAdapter;
import com.example.futanalyzer.informacoes.InformacoesApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import modelDominio.Jogador;

public class JogadoresActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rvJogadores;
    ListaJogadoresAdapter listaJogadoresAdapter;

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

        if(informacoesApp.getListaJogador() != null){
            listaJogadoresAdapter = new ListaJogadoresAdapter(informacoesApp.getListaJogador(), trataCliqueItem);
            rvJogadores.setLayoutManager(new LinearLayoutManager(JogadoresActivity.this));
            rvJogadores.setItemAnimator(new DefaultItemAnimator());
            rvJogadores.setAdapter(listaJogadoresAdapter);
        }
    }

    ListaJogadoresAdapter.JogadorOnClickListener trataCliqueItem = new ListaJogadoresAdapter.JogadorOnClickListener() {
        @Override
        public void onClickJogador(View view, int position) {
            Jogador meuJogador = informacoesApp.getListaJogador().get(position);

        }
    };
}