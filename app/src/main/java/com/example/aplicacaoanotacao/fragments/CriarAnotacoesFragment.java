package com.example.aplicacaoanotacao.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aplicacaoanotacao.databinding.CriarAnotacoesFragmentBinding;
import com.example.aplicacaoanotacao.R;
import com.example.aplicacaoanotacao.models.Anotacao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CriarAnotacoesFragment extends Fragment {

    private  CriarAnotacoesFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CriarAnotacoesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.botaoSalvarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descricao = String.valueOf(binding.descricaoCampo.getText());
                String titulo = String.valueOf(binding.tituloCampo.getText());

                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("Anotacoes", 0);

                Anotacao novaAnotacao = new Anotacao(titulo, descricao);

                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

                Gson gson = new Gson();
                String json = sharedPreferences.getString("Anotacoes", "");

                if(json != null && !json.equals("")) {
                    ArrayList<Anotacao> listAnotacao = gson.fromJson(json, ArrayList.class);

                    listAnotacao.add(novaAnotacao);

                    String jsonNovaAnotacao = gson.toJson(listAnotacao);
                    prefsEditor.putString("Anotacoes", jsonNovaAnotacao);
                    prefsEditor.commit();
                } else {
                    ArrayList<Anotacao> listAnotacao = new ArrayList<Anotacao>();
                    listAnotacao.add(novaAnotacao);
                    String jsonNovaAnotacao = gson.toJson(listAnotacao);
                    prefsEditor.putString("Anotacoes", jsonNovaAnotacao);
                    prefsEditor.commit();
                }

                NavHostFragment.findNavController(CriarAnotacoesFragment.this)
                        .navigate(R.id.voltarAnotacoes);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}