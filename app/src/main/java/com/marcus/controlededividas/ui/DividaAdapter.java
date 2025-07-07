package com.marcus.controlededividas.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marcus.controlededividas.R;
import com.marcus.controlededividas.data.Divida;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DividaAdapter extends RecyclerView.Adapter<DividaAdapter.DividaViewHolder> {

    private List<Divida> dividas = new ArrayList<>();
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Divida divida);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public DividaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_divida, parent, false);
        return new DividaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DividaViewHolder holder, int position) {
        Divida dividaAtual = dividas.get(position);
        holder.textViewDescricao.setText(dividaAtual.getDescricao());
        holder.textViewData.setText(dividaAtual.getDataFormatada());
        holder.textViewValor.setText(String.format(Locale.getDefault(), "R$ %.2f", dividaAtual.getValor()));

        holder.imageViewDelete.setOnClickListener(v -> {
            if (onDeleteClickListener != null && position != RecyclerView.NO_POSITION) {
                onDeleteClickListener.onDeleteClick(dividas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dividas.size();
    }

    public void setDividas(List<Divida> dividas) {
        this.dividas = dividas;
        notifyDataSetChanged();
    }

    class DividaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDescricao;
        private final TextView textViewData;
        private final TextView textViewValor;
        final ImageView imageViewDelete;

        public DividaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}