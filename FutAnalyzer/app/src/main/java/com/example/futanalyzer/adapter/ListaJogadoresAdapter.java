package com.example.futanalyzer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modelDominio.Jogador;

public class ListaJogadoresAdapter extends RecyclerView.Adapter<ListaJogadoresAdapter.MyViewHolder>{
    private List<Jogador> listaJogador;
    private JogadorOnClickListener jogadorOnClickListener;

    public ListaJogadoresAdapter(List<Jogador> listaJogador, JogadorOnClickListener jogadorOnClickListener){
        this.listaJogador = listaJogador;
        this.jogadorOnClickListener = jogadorOnClickListener;
    }



    @Override
    public void onBindViewHolder(@NonNull ListaJogadoresAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView
    }

    public interface JogadorOnClickListener{
        public void onClickJogador(View view, int position);
    }
}
