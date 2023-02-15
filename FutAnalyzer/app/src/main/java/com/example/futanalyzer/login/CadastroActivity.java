package com.example.futanalyzer.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.futanalyzer.InformacoesApp;
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
                if (!etUsuarioCadastro.getText().toString().equals("")) {
                    if (!etEmailCadastro.getText().toString().equals("")) {
                        if (!etSenhaCadastro.getText().toString().equals("")) {

                            String usuario = etUsuarioCadastro.getText().toString();
                            String email = etEmailCadastro.getText().toString();
                            String senha = etSenhaCadastro.getText().toString();

                            meuUsuario = new Usuario(senha, usuario, email);

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        informacoesApp.out.writeObject("UsuarioInserir");
                                        msgRecebida = (String) informacoesApp.in.readObject();
                                        if (msgRecebida.equals("ok")) {
                                            informacoesApp.out.writeObject(meuUsuario);
                                            msgRecebida = (String) informacoesApp.in.readObject();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                  //  Toast.makeText(informacoesApp, "Recebido" + msgRecebida, Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(informacoesApp, "Usuario cadastrado", Toast.LENGTH_SHORT).show();
                                                    Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                                                    startActivity(it);
                                                }
                                            });
                                        }
                                    } catch (IOException ioe) {
                                        ioe.printStackTrace();
                                    } catch (ClassNotFoundException classe) {
                                        classe.printStackTrace();
                                    }
                                }
                            });
                            thread.start();

//                            Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
//                            startActivity(it);
                        } else {
                            etSenhaCadastro.setError("Erro: Informe a senha");
                            etSenhaCadastro.requestFocus();
                        }
                    } else {
                        etEmailCadastro.setError("Erro: Informe o email");
                        etEmailCadastro.requestFocus();
                    }
                } else {
                    etUsuarioCadastro.setError("Erro: Informe o usuário");
                    etUsuarioCadastro.requestFocus();
                }
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
                Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }

    public void limpaCampos(){
        etSenhaCadastro.setText("");
        etEmailCadastro.setText("");
        etSenhaCadastro.setText("");
    }
}
