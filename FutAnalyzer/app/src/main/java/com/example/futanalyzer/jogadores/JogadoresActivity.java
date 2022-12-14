package com.example.futanalyzer.jogadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futanalyzer.R;
import com.example.futanalyzer.informacoes.InformacoesApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class JogadoresActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rvJogadores;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(JogadoresActivity.this, CadastroJogadorActivity.class);
                startActivity(it);
            }
        });

    }
}