package com.example.futanalyzer.ui.jogos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.MainActivity;
import com.example.futanalyzer.R;

import java.io.IOException;

import modelDominio.Jogo;

public class CadastroJogoActivity extends AppCompatActivity {

    EditText etGolMeu, etGolAdv;
    Button btCancelar, btCadastrar;

    InformacoesApp informacoesApp;
    Jogo meuJogo;
    String msgRecebida;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_jogo);

        etGolMeu = findViewById(R.id.etMeuTimeCadastro);
        etGolAdv = findViewById(R.id.etMeuAdvCadastro);
        btCadastrar = findViewById(R.id.btJogoCadastra);
        btCancelar = findViewById(R.id.btCancelarCadastrarJogo);

        informacoesApp = (InformacoesApp) getApplicationContext();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etGolMeu.getText().toString().equals("")){
                    if(!etGolAdv.getText().toString().equals("")){
                        int golFeito = Integer.parseInt(etGolMeu.getText().toString());
                        int golSofrido = Integer.parseInt(etGolAdv.getText().toString());

                        meuJogo = new Jogo(golFeito, golSofrido, informacoesApp.getUsuarioLogado().getCod());

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    informacoesApp.out.writeObject("JogoInserir");
                                    msgRecebida = (String) informacoesApp.in.readObject();
                                    if(msgRecebida.equals("ok")){
                                        informacoesApp.out.writeObject(meuJogo);
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(informacoesApp, "Jogo cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                                Intent it = new Intent(CadastroJogoActivity.this, MainActivity.class);
                                                startActivity(it);
                                                limpaCampos();
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
                        etGolAdv.setError("Erro: informe o número de gols feitos pelo adversário");
                        etGolAdv.requestFocus();
                    }
                } else {
                    etGolMeu.setError("Erro: informe seu número de gols feitos");
                    etGolMeu.requestFocus();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
                finish();
            }
        });
    }

    public void limpaCampos(){
        etGolMeu.setText("");
        etGolAdv.setText("");
    }
}