package com.example.futanalyzer;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futanalyzer.informacoes.InformacoesApp;
import com.example.futanalyzer.jogadores.JogadoresActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.futanalyzer.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    MenuItem botaoJogadores;
    private ActivityMainBinding binding;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_atual, R.id.navigation_estatistica)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        botaoJogadores = findViewById(R.id.jogadores);

        //obtendo o contexto
//        informacoesApp = (InformacoesApp) getApplicationContext();
//
//        //criando thread
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    //criando conexão com o servidor
//                    informacoesApp.socket = new Socket("10.0.2.2", 12345);
//                    informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
//                    informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());
//
//                    //sincronizando thread
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(informacoesApp, "Conexão efetuada com sucesso", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } catch (IOException ioe){
//                    ioe.printStackTrace();
//                }
//            }
//        });
//        thread.start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.jogadores){
            Intent it = new Intent(MainActivity.this, JogadoresActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_jogadores, menu);
        return true;

    }

}