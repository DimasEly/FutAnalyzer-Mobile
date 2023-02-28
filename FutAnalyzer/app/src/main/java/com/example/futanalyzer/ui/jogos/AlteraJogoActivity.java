package com.example.futanalyzer.ui.jogos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.MainActivity;
import com.example.futanalyzer.R;
import com.example.futanalyzer.jogadores.JogadoresActivity;

import java.io.IOException;

import modelDominio.Jogo;

public class AlteraJogoActivity extends AppCompatActivity {
    EditText etAlterarMeuPlacar, etAlterarAdvPlacar;
    Button btSalvarAlterarJogo, btCancelarAlterarJogo;
    int id, idUser;

    InformacoesApp informacoesApp;
    Jogo meuJogoAlterar;
    String msgRecebida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_jogo);
        etAlterarMeuPlacar = findViewById(R.id.etMeuTAltera);
        etAlterarAdvPlacar = findViewById(R.id.etMeuAdvAltera);
        btSalvarAlterarJogo = findViewById(R.id.btJogoAltera);
        btCancelarAlterarJogo = findViewById(R.id.btCancelarAlterarJogo);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Intent it = getIntent();

        if(it != null && it.hasExtra("jogo")){
            Jogo meuJogo = (Jogo) it.getSerializableExtra("jogo");

            id = meuJogo.getId();
            idUser = meuJogo.getIdUsuario();
            etAlterarMeuPlacar.setText(String.valueOf(meuJogo.getMeuPlacar()));
            etAlterarAdvPlacar.setText(String.valueOf(meuJogo.getAdvPlacar()));
        }

        btSalvarAlterarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etAlterarMeuPlacar.getText().toString().equals("")){
                    if(!etAlterarAdvPlacar.getText().toString().equals("")){
                        int meuPlacar = Integer.parseInt(etAlterarMeuPlacar.getText().toString());
                        int advPlacar = Integer.parseInt(etAlterarAdvPlacar.getText().toString());
                        meuJogoAlterar = new Jogo(id, meuPlacar, advPlacar, idUser);

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    informacoesApp.out.writeObject("JogoAlterar");
                                    msgRecebida = (String) informacoesApp.in.readObject();
                                    if(msgRecebida.equals("ok")){
                                        informacoesApp.out.writeObject(meuJogoAlterar);
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent it = new Intent(AlteraJogoActivity.this, MainActivity.class);
                                                startActivity(it);
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
                        etAlterarAdvPlacar.setError("Erro: Informe o placar do oponente");
                        etAlterarAdvPlacar.requestFocus();
                    }
                } else {
                    etAlterarMeuPlacar.setError("Erro: informe o seu placar");
                    etAlterarMeuPlacar.requestFocus();
                }
            }
        });

        btCancelarAlterarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AlteraJogoActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}