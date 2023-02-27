package com.example.futanalyzer.ui.jogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.R;
import com.example.futanalyzer.adapter.ListaJogosAdapter;
import com.example.futanalyzer.databinding.FragmentHomeBinding;
import com.example.futanalyzer.jogadores.JogadorAlterarActivity;
import com.example.futanalyzer.jogadores.JogadoresActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Jogo;
import modelDominio.Usuario;

public class JogosFragment extends Fragment {
    FloatingActionButton fabAddJogo;
    RecyclerView rvListadeJogo;
    ListaJogosAdapter jogosAdapter;

    ArrayList<Jogo> listaJogos;

    InformacoesApp informacoesApp;
    String msgRecebida;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        JogosViewModel jogosViewModel =
                new ViewModelProvider(this).get(JogosViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvListadeJogo = binding.rvListaJogos;
        fabAddJogo = binding.fabAddPartida;

        informacoesApp = (InformacoesApp) getActivity().getApplication();

        fabAddJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CadastroJogoActivity.class);
                startActivity(it);
                getActivity().finish();
            }
        });

        return root;
    }

    private void carregaListaJogos() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    informacoesApp.out.writeObject("JogoLista");
                    Usuario userLogado = informacoesApp.getUsuarioLogado();
                    informacoesApp.out.writeObject(userLogado);
                    listaJogos = (ArrayList<Jogo>) informacoesApp.in.readObject();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jogosAdapter = new ListaJogosAdapter(listaJogos, trataCliqueItem, trataCliqueLongo);
                            rvListadeJogo.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvListadeJogo.setItemAnimator(new DefaultItemAnimator());
                            rvListadeJogo.setAdapter(jogosAdapter);
                        }
                    });
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (ClassNotFoundException classe) {
                    classe.printStackTrace();
                }
            }
        });
        thread.start();
    }

    ListaJogosAdapter.JogoOnClickListener trataCliqueItem = new ListaJogosAdapter.JogoOnClickListener() {
            @Override
            public void onJogoClick(View view, int position) {
                Jogo meuJogo = listaJogos.get(position);
                Intent it = new Intent(getActivity(), AlteraJogoActivity.class);
                it.putExtra("jogo", meuJogo);
                startActivity(it);
                getActivity().finish();

            }
        };


        ListaJogosAdapter.JogoOnLongClickListener trataCliqueLongo = new ListaJogosAdapter.JogoOnLongClickListener() {
            @Override
            public void onJogoLongClick(View view, int position) {
                Jogo meuJogo = listaJogos.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir o jogo?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Thread thread2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    informacoesApp.out.writeObject("JogoExcluir");
                                    msgRecebida = (String) informacoesApp.in.readObject();
                                    if(msgRecebida.equals("ok")){
                                        informacoesApp.out.writeObject(meuJogo.getId());
                                        msgRecebida = (String) informacoesApp.in.readObject();

                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(informacoesApp, "Jogo excluído com sucesso", Toast.LENGTH_SHORT).show();
                                                carregaListaJogos();
                                            }
                                        });
                                    }
                                } catch (IOException ioe){
                                    ioe.printStackTrace();
                                } catch (ClassNotFoundException classe){
                                    classe.printStackTrace();
                                }
                            }
                        });
                        thread2.start();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        };

    @Override
    public void onResume() {
        super.onResume();
        carregaListaJogos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}