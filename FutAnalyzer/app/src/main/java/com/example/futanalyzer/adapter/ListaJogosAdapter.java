package com.example.futanalyzer.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futanalyzer.R;

import java.util.List;

import modelDominio.Jogo;

public class ListaJogosAdapter extends RecyclerView.Adapter<ListaJogosAdapter.MyViewHolder>{
    private List<Jogo> listaJogos;
    private JogoOnClickListener jogoOnClickListener;
    private JogoOnLongClickListener jogoOnLongClickListener;

    public ListaJogosAdapter (List<Jogo> listaJogos, JogoOnClickListener jogoOnClickListener, JogoOnLongClickListener jogoOnLongClickListener){
        this.listaJogos = listaJogos;
        this.jogoOnClickListener = jogoOnClickListener;
        this.jogoOnLongClickListener = jogoOnLongClickListener;
    }

    @Override
    public ListaJogosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jogo_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListaJogosAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Jogo meuJogo = listaJogos.get(position);
        holder.tvMeuTime.setText(String.valueOf(meuJogo.getMeuPlacar()));
        holder.tvOponente.setText(String.valueOf(meuJogo.getAdvPlacar()));

        if(jogoOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogoOnClickListener.onJogoClick(holder.itemView, position);
                }
            });
        }

        if(jogoOnLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    jogoOnLongClickListener.onJogoLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaJogos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvMeuTime, tvOponente;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvMeuTime = (TextView) itemView.findViewById(R.id.tvMeuTime);
            tvOponente = (TextView) itemView.findViewById(R.id.tvOponente);
        }
    }
    public interface JogoOnClickListener{
        public void onJogoClick(View view, int position);
    }

    public interface JogoOnLongClickListener{
        public void onJogoLongClick(View view, int position);
    }
}