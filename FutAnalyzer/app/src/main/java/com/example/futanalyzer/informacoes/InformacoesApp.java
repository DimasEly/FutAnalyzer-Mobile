package com.example.futanalyzer.informacoes;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Jogador;

public class InformacoesApp extends Application {
    private ArrayList<Jogador> listaJogador;

    @Override
    public void onCreate() {
        super.onCreate();
        this.listaJogador = new ArrayList<>();
    }

    public ArrayList<Jogador> getListaJogador() {
        return listaJogador;
    }

    public void setListaJogador(ArrayList<Jogador> listaJogador) {
        this.listaJogador = listaJogador;
    }

//    public Socket socket;
//    public ObjectOutputStream out;
//    public ObjectInputStream in;
}
