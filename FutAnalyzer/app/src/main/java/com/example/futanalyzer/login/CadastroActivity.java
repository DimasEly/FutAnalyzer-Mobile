package com.example.futanalyzer.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.R;

import java.io.IOException;

import modelDominio.Usuario;

public class CadastroActivity extends AppCompatActivity {
    EditText etUsuarioCadastro, etEmailCadastro, etSenhaCadastro;
    Button btCadastrar, bCancelar;

    InformacoesApp informacoesApp;
    Usuario meuUsuario;
    String msgRecebida;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        etUsuarioCadastro = findViewById(R.id.etNomeCadastroUsuario);
        etEmailCadastro = findViewById(R.id.etEmailCadastroUsuario);
        etSenhaCadastro = findViewById(R.id.etSenhaCadastroUsuario);
        btCadastrar = findViewById(R.id.btUsuarioCadastro);
        bCancelar = findViewById(R.id.btUsuarioCadastroCancelar);

        informacoesApp = (InformacoesApp) getApplicationContext();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etUsuarioCadastro.getText().toString().equals("")){
                    if(!etEmailCadastro.getText().toString().equals("")){
                        if(!etSenhaCadastro.getText().toString().equals("")){
                            String nome = etUsuarioCadastro.getText().toString();
                            String email = etEmailCadastro.getText().toString();
                            String senha = etSenhaCadastro.getText().toString();

                            meuUsuario = new Usuario(nome, email, senha);

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        informacoesApp.out.writeObject("cadastroUsuario");
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        if (msgRecebida.equals("Ok")){
                                            informacoesApp.out.writeObject(meuUsuario);
                                            msgRecebida = (String) informacoesApp.in.readObject();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(informacoesApp, "Recebido" + msgRecebida, Toast.LENGTH_SHORT).show();
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

                            Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                            startActivity(it);
                        } else {
                            etSenhaCadastro.setError("Erro: Informe a senha");
                            etSenhaCadastro.requestFocus();
                        }
                    } else{
                        etEmailCadastro.setError("Erro: Informe o email");
                        etEmailCadastro.requestFocus();
                    }
                } else {
                    etUsuarioCadastro.setError("Erro: Informe o usu??rio");
                    etUsuarioCadastro.requestFocus();
                }
            }
        });

    }
}