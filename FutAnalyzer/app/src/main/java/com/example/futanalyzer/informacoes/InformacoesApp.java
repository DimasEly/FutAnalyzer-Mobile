package com.example.futanalyzer.informacoes;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InformacoesApp extends Application {
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
}