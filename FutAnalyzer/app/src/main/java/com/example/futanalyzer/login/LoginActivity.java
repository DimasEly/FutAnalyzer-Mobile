package com.example.futanalyzer.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.MainActivity;
import com.example.futanalyzer.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDominio.Usuario;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginUsuario, etSenhaUsuario;
    Button btEntrarUsuario, btCadastrarUsuario;
    TextView tvEsqueceuSenha;

    InformacoesApp informacoesApp;
    Usuario usuario;
    String msgRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUsuario = findViewById(R.id.etNomeUsuario);
        etSenhaUsuario = findViewById(R.id.etSenhaUsuario);
        btEntrarUsuario = findViewById(R.id.btUsuarioLogin);
        btCadastrarUsuario = findViewById(R.id.btCadastrarLogin);
        tvEsqueceuSenha = findViewById(R.id.tvEsqueceuSenha);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    informacoesApp.socket = new Socket("10.0.2.2", 12345);
                    informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
                    informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        });
        thread.start();


        btEntrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etLoginUsuario.getText().toString().equals("")){
                    if(!etSenhaUsuario.getText().toString().equals("")){
                        String usuarioUsuario = etLoginUsuario.getText().toString();
                        String senha = etSenhaUsuario.getText().toString();

                        usuario = new Usuario(usuarioUsuario, senha);

                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    informacoesApp.out.writeObject("UsuarioEfetuarLogin");
                                    msgRecebida = (String) informacoesApp.in.readObject();
                                    if (msgRecebida.equals("ok")) {
                                        informacoesApp.out.writeObject(usuario);
                                        usuario = (Usuario) informacoesApp.in.readObject();


                                        if (usuario != null) {
                                            informacoesApp.setUsuarioLogado(usuario);

                                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(it);
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(informacoesApp, "ATENÇÃO: Usuário e senha não conferem", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(informacoesApp, "Erro ao tentar se autenticar", Toast.LENGTH_SHORT).show();
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
                        thread1.start();
                    } else {
                        etSenhaUsuario.setError("Erro: Informe a senha");
                        etSenhaUsuario.requestFocus();
                    }
                } else {
                    etLoginUsuario.setError("Erro: Informe o login");
                    etLoginUsuario.requestFocus();
                }
            }
        });

        btCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        tvEsqueceuSenha.setPaintFlags(tvEsqueceuSenha.getPaintFlags());
        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });
    }
}