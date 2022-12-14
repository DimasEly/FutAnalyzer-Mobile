package com.example.futanalyzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jogador_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaJogadoresAdapter.MyViewHolder holder, int position) {
        Jogador meuJogador = listaJogador.get(position);
        holder.tvNomeJogador.setText(meuJogador.getNome());
        holder.tvOverallJogador.setText(meuJogador.getOverall());
        holder.tvGolsJogador.setText(meuJogador.getGol());

        if(jogadorOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogadorOnClickListener.onClickJogador(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaJogador.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNomeJogador, tvOverallJogador, tvGolsJogador;
        public MyViewHolder(View itemView){
            super(itemView);
            tvNomeJogador = (TextView) itemView.findViewById(R.id.jogador_nome);
            tvOverallJogador = (TextView) itemView.findViewById(R.id.jogador_over);
            tvGolsJogador = (TextView)  itemView.findViewById(R.id.jogador_gol);
        }
    }

    public interface JogadorOnClickListener{
        public void onClickJogador(View view, int position);
    }
}
