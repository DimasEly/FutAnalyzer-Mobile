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
    private List<Jogador> listaJogadores;
    private JogadorOnClickListener jogadorOnClickListener;
    private JogadorOnLongClickListener jogadorOnLongClickListener;

    public ListaJogadoresAdapter(List<Jogador> listaJogadores, JogadorOnClickListener jogadorOnClickListener, JogadorOnLongClickListener jogadorOnLongClickListener){
        this.listaJogadores = listaJogadores;
        this.jogadorOnClickListener = jogadorOnClickListener;
        this.jogadorOnLongClickListener = jogadorOnLongClickListener;
    }

    public ListaJogadoresAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jogador_item, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final ListaJogadoresAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position){
        Jogador meuJogador = listaJogadores.get(position);
        holder.tvJogadorNome.setText(meuJogador.getNome());
        holder.tvJogadorOverall.setText(String.valueOf(meuJogador.getOverall()));

        if(jogadorOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogadorOnClickListener.onJogadorClick(holder.itemView, position);
                }
            });
        if(jogadorOnLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    jogadorOnLongClickListener.onJogadorLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
        }
    }

    @Override
    public int getItemCount() {
        return listaJogadores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvJogadorNome, tvJogadorOverall;
        public MyViewHolder(View itemView){
            super(itemView);
            tvJogadorNome = (TextView) itemView.findViewById(R.id.jogador_nome);
            tvJogadorOverall = (TextView) itemView.findViewById(R.id.jogador_over);
        }
    }

    public interface JogadorOnClickListener{
        public void onJogadorClick(View view, int position);
    }

    public interface JogadorOnLongClickListener{
        public void onJogadorLongClick(View view, int position);
    }
}
