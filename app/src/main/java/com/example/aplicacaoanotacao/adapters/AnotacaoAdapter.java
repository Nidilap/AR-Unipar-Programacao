package com.example.aplicacaoanotacao.adapters;

import com.example.aplicacaoanotacao.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacaoanotacao.models.Anotacao;

import java.util.ArrayList;

public class AnotacaoAdapter extends RecyclerView.Adapter<AnotacaoAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Anotacao> anotacaoModelArrayList;

    // Constructor
    public AnotacaoAdapter(Context context, ArrayList<Anotacao> anotacaoModelArrayList) {
        this.context = context;
        this.anotacaoModelArrayList = anotacaoModelArrayList;
    }

    @NonNull
    @Override
    public AnotacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnotacaoAdapter.ViewHolder holder, int position) {
        Anotacao model = anotacaoModelArrayList.get(position);
        holder.nome.setText(model.getNome());
        holder.descricao.setText("" + model.getDescricao());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return anotacaoModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nome;
        private final TextView descricao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.idNome);
            descricao = itemView.findViewById(R.id.idDescricao);
        }
    }

}