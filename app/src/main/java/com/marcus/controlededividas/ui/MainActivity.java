package com.marcus.controlededividas.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import com.marcus.controlededividas.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DividaAdapter.OnDeleteClickListener {

    private MainViewModel mainViewModel;
    private TextView textViewTotalValor;
    private DividaAdapter dividaAdapter;
    private Spinner spinnerUsuarios;
    private ArrayAdapter<User> userAdapter;

    private List<User> listaDeUsuarios = new ArrayList<>();
    private User usuarioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotalValor = findViewById(R.id.textViewTotalValor);
        spinnerUsuarios = findViewById(R.id.spinnerUsuarios);
        ImageButton buttonAddUser = findViewById(R.id.buttonAddUser);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDividas);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        dividaAdapter = new DividaAdapter();
        recyclerView.setAdapter(dividaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dividaAdapter.setOnDeleteClickListener(this);

        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDeUsuarios);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuarios.setAdapter(userAdapter);

        mainViewModel.getAllUsers().observe(this, users -> {
            listaDeUsuarios.clear();
            listaDeUsuarios.addAll(users);
            userAdapter.notifyDataSetChanged();

            if (users.isEmpty()) {
                mostrarDialogoAdicionarUsuario(true);
            } else if (usuarioSelecionado == null) {
                spinnerUsuarios.setSelection(0);
            }
        });

        mainViewModel.getDividasDoUsuario().observe(this, dividas -> {
            dividaAdapter.setDividas(dividas);
        });

        mainViewModel.getTotalDaDividaDoUsuario().observe(this, total -> {
            if (total == null) {
                textViewTotalValor.setText(String.format(Locale.getDefault(), "R$ %.2f", 0.0));
            } else {
                textViewTotalValor.setText(String.format(Locale.getDefault(), "R$ %.2f", total));
            }
        });

        spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usuarioSelecionado = (User) parent.getItemAtPosition(position);
                mainViewModel.setCurrentUser(usuarioSelecionado.id);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        buttonAddUser.setOnClickListener(v -> mostrarDialogoAdicionarUsuario(false));

        FloatingActionButton fab = findViewById(R.id.fabAdicionar);
        fab.setOnClickListener(view -> {
            if (usuarioSelecionado == null) {
                Toast.makeText(this, "Selecione ou crie um usuário primeiro", Toast.LENGTH_SHORT).show();
            } else {
                mostrarDialogoAdicionarDivida();
            }
        });
    }

    private void mostrarDialogoAdicionarDivida() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_divida, null);
        builder.setView(view);

        final EditText inputDescricao = view.findViewById(R.id.inputDescricao);
        final EditText inputValor = view.findViewById(R.id.inputValor);

        builder.setTitle("Nova Dívida para " + usuarioSelecionado.name)
                .setPositiveButton("Adicionar", (dialog, which) -> {
                    String descricao = inputDescricao.getText().toString();
                    String valorStr = inputValor.getText().toString();

                    if (descricao.isEmpty() || valorStr.isEmpty()) { /* ... */ return; }

                    try {
                        double valor = Double.parseDouble(valorStr);
                        // AQUI ESTÁ A MUDANÇA: Usamos o ID do usuário selecionado
                        Divida novaDivida = new Divida(descricao, valor, usuarioSelecionado.id);
                        mainViewModel.insert(novaDivida);
                        Toast.makeText(this, "Dívida adicionada!", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) { /* ... */ }
                })
                .setNegativeButton("Cancelar", null);

        builder.create().show();
    }

    // NOVO MÉTODO: Diálogo para adicionar um novo usuário
    private void mostrarDialogoAdicionarUsuario(boolean isPrimeiroUsuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);
        builder.setView(view);

        final EditText inputNome = view.findViewById(R.id.inputNomeUsuario);

        builder.setTitle(isPrimeiroUsuario ? "Crie o Primeiro Usuário" : "Novo Usuário")
                .setMessage(isPrimeiroUsuario ? "Para começar, crie um perfil de usuário." : null)
                .setPositiveButton("Salvar", (dialog, which) -> {
                    String nome = inputNome.getText().toString();
                    if (!nome.isEmpty()) {
                        mainViewModel.insertUser(new User(nome));
                    } else {
                        Toast.makeText(this, "O nome não pode ser vazio", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(isPrimeiroUsuario ? null : "Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(!isPrimeiroUsuario);
        dialog.show();
    }

    @Override
    public void onDeleteClick(Divida divida) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar Exclusão")
                .setMessage("Remover \"" + divida.getDescricao() + "\"?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    mainViewModel.deleteById(divida.getId());
                    Toast.makeText(MainActivity.this, "Dívida removida!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}