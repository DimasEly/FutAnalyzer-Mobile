package com.example.futanalyzer;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Jogador;
import modelDominio.Usuario;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Usuario usuarioLogado;
    private ArrayList<Jogador> listaJogadores;

    @Override
    public void onCreate() {
        super.onCreate();
        this.listaJogadores = new ArrayList<>();
    }

    public ArrayList<Jogador> getListaJogadores(){ return  listaJogadores;}

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
