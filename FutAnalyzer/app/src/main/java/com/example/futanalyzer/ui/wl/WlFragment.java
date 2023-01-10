package com.example.futanalyzer.ui.wl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.futanalyzer.R;
import com.example.futanalyzer.databinding.FragmentHomeBinding;
import com.example.futanalyzer.jogadores.JogadoresActivity;
import com.example.futanalyzer.jogadores.ListaJogador;

public class WlFragment extends Fragment {
    Button teste;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WlViewModel wlViewModel =
                new ViewModelProvider(this).get(WlViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button botao = binding.teste;
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), ListaJogador.class);
                startActivity(it);
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}