package com.example.futanalyzer.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.futanalyzer.R;

import java.util.List;

import modelDominio.Jogador;

public class ListaJogadoresAdapter extends RecyclerView.Adapter<ListaJogadoresAdapter.MyViewHolder>{
    private List<Jogador> listaJogador;
    private JogadorOnClickListener jogadorOnClickListener;

    public ListaJogadoresAdapter(List<Jogador> listaJogador, JogadorOnClickListener jogadorOnClickListener){
        this.listaJogador = listaJogador;
        this.jogadorOnClickListener = jogadorOnClickListener;
    }

    public ListaJogadoresAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jogador_item, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final ListaJogadoresAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position){
        Jogador meuJogador = listaJogador.get(position);
        holder.tvJogadorNome.setText(meuJogador.getNome());
        holder.tvJogadorOverall.setText(String.valueOf(meuJogador.getOverall()));
        holder.tvJogadorGol.setText(String.valueOf(meuJogador.getGol()));

        if(jogadorOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogadorOnClickListener.onJogadorClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaJogador.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvJogadorGol, tvJogadorNome, tvJogadorOverall;
        public MyViewHolder(View itemView){
            super(itemView);
            tvJogadorGol = (TextView) itemView.findViewById(R.id.jogador_gol);
            tvJogadorNome = (TextView) itemView.findViewById(R.id.jogador_nome);
            tvJogadorOverall = (TextView) itemView.findViewById(R.id.jogador_over);
        }
    }

    public interface JogadorOnClickListener{
        public void onJogadorClick(View view, int position);
    }
}
