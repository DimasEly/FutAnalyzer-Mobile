package com.example.futanalyzer.ui.estatistica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.futanalyzer.InformacoesApp;
import com.example.futanalyzer.adapter.ListaJogosAdapter;
import com.example.futanalyzer.databinding.FragmentDashboardBinding;

import java.io.IOException;
import java.util.ArrayList;

import modelDominio.Jogo;
import modelDominio.Usuario;

public class EstatisticasFragment extends Fragment {
    ArrayList<Jogo> listaVitorias, listaEmpates, listaDerrotas;
    TextView numeroVitorias, numeroEmpates, numeroDerrotas, numeroGolsFeitos, numeroGolsSofridos, numeroJogos, mediaGolsFeitos, mediaGolsSofridos, saldoGols, aproveitamento;
    int golsFeitos, golsSofridos;

    InformacoesApp informacoesApp;
    String msgRecebida;

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstatisticasViewModel estatisticasViewModel =
                new ViewModelProvider(this).get(EstatisticasViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        numeroJogos = binding.textJogos;
        numeroVitorias = binding.textVitorias;
        numeroEmpates = binding.textEmpates;
        numeroDerrotas = binding.textDerrotas;
        numeroGolsFeitos = binding.textGolsFeitos;
        numeroGolsSofridos = binding.textGolsSofridos;
        mediaGolsFeitos = binding.textGolsFeitosPjogo;
        mediaGolsSofridos = binding.textGolsSofridosPjogo;
        saldoGols = binding.textSaldo;
        aproveitamento = binding.textAproveitamento;


        informacoesApp = (InformacoesApp) getActivity().getApplication();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    informacoesApp.out.writeObject("JogoListasEstatisticas");
                    Usuario userLogado = informacoesApp.getUsuarioLogado();
                    informacoesApp.out.writeObject(userLogado);
                    listaVitorias = (ArrayList<Jogo>) informacoesApp.in.readObject();
                    listaEmpates = (ArrayList<Jogo>) informacoesApp.in.readObject();
                    listaDerrotas = (ArrayList<Jogo>) informacoesApp.in.readObject();
                    golsFeitos = (int) informacoesApp.in.readObject();
                    golsSofridos = (int) informacoesApp.in.readObject();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int numVit = listaVitorias.size();
                            numeroVitorias.setText(String.valueOf(numVit));

                            int numEmp = listaEmpates.size();
                            numeroEmpates.setText(String.valueOf(numEmp));

                            int numDer = listaDerrotas.size();
                            numeroDerrotas.setText(String.valueOf(numDer));

                            int numGolMeu = golsFeitos;
                            numeroGolsFeitos.setText(String.valueOf(numGolMeu));

                            int numGolAdv = golsSofridos;
                            numeroGolsSofridos.setText(String.valueOf(numGolAdv));

                            int saldoDeGols = numGolMeu - numGolAdv;
                            saldoGols.setText(String.valueOf(saldoDeGols));

                            int numJogos = numVit+ numEmp + numDer;
                            numeroJogos.setText(String.valueOf(numJogos));

                            if(numJogos > 0){
                                double medGolsFeitosAux = (double) numGolMeu/numJogos;
                                float medGolsFeitos = (float) medGolsFeitosAux;
                                mediaGolsFeitos.setText(String.valueOf(medGolsFeitos));

                                double medGolsSofridosAux = (double) numGolAdv/numJogos;
                                float medGolsSofridos = (float) medGolsSofridosAux;
                                mediaGolsSofridos.setText(String.valueOf(medGolsSofridos));

                                double aprovAux =  ( (double) (numVit*3 + numEmp) / (double) (numJogos*3)) * 100;
                                int aprov = (int) aprovAux;
                                aproveitamento.setText(String.valueOf(aprov));


                            } else {
                                mediaGolsFeitos.setText(String.valueOf(0));
                                mediaGolsSofridos.setText(String.valueOf(0));
                                aproveitamento.setText(String.valueOf(0));
                            }

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}