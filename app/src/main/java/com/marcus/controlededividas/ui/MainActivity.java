package com.marcus.controlededividas.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcus.controlededividas.R;
import com.marcus.controlededividas.data.Divida;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DividaAdapter.OnDeleteClickListener {

    private MainViewModel mainViewModel;
    private TextView textViewTotalValor;
    private DividaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotalValor = findViewById(R.id.textViewTotalValor);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDividas);

        adapter = new DividaAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnDeleteClickListener(this);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getAllDividas().observe(this, dividas -> adapter.setDividas(dividas));

        mainViewModel.getTotalDivida().observe(this, total -> {
            if (total == null) {
                textViewTotalValor.setText(String.format(Locale.getDefault(), "R$ %.2f", 0.0));
            } else {
                textViewTotalValor.setText(String.format(Locale.getDefault(), "R$ %.2f", total));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabAdicionar);
        fab.setOnClickListener(view -> mostrarDialogoAdicionarDivida());
    }

    private void mostrarDialogoAdicionarDivida() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_divida, null);
        builder.setView(view);

        final EditText inputDescricao = view.findViewById(R.id.inputDescricao);
        final EditText inputValor = view.findViewById(R.id.inputValor);

        builder.setTitle("Nova Dívida")
                .setPositiveButton("Adicionar", (dialog, which) -> {
                    String descricao = inputDescricao.getText().toString();
                    String valorStr = inputValor.getText().toString();

                    if (descricao.isEmpty() || valorStr.isEmpty()) {
                        Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        double valor = Double.parseDouble(valorStr);
                        mainViewModel.insert(new Divida(descricao, valor));
                        Toast.makeText(this, "Dívida adicionada!", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.create().show();
    }

    @Override
    public void onDeleteClick(Divida divida) {
        // Confirmação antes de deletar
        new AlertDialog.Builder(this)
                .setTitle("Confirmar Exclusão")
                .setMessage("Tem certeza que deseja remover esta dívida?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    mainViewModel.deleteById(divida.getId());
                    Toast.makeText(MainActivity.this, "Dívida removida!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}