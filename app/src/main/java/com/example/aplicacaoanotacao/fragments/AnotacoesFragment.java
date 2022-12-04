package com.example.aplicacaoanotacao.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacaoanotacao.R;
import com.example.aplicacaoanotacao.adapters.AnotacaoAdapter;
import com.example.aplicacaoanotacao.models.Anotacao;
import com.example.aplicacaoanotacao.databinding.AnotacoesFragmentBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AnotacoesFragment extends Fragment {

    private AnotacoesFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = AnotacoesFragmentBinding.inflate(inflater, container, false);

        RecyclerView anotacaoRV = binding.idAnotacaoView;

        Context context = getActivity().getBaseContext();

        // Adiciona itens na lista
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("Anotacoes", 0);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("Anotacoes", "");

        if(json != null && !json.equals("")) {

            Type listOfMyClassObject = new TypeToken<ArrayList<Anotacao>>() {}.getType();
            ArrayList<Anotacao> listAnotacao = gson.fromJson(json, listOfMyClassObject);

            // Inicializa o adapter e passa a lista.
            AnotacaoAdapter anotacaoAdapter = new AnotacaoAdapter(context, listAnotacao);

            // Seta a recycler view
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

            // Seta o layoutmanager e a recycler view dentro da view.
            anotacaoRV.setLayoutManager(linearLayoutManager);
            anotacaoRV.setAdapter(anotacaoAdapter);
        }

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.botaoCriarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AnotacoesFragment.this)
                        .navigate(R.id.actionGoToCriarAnotacoes);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}