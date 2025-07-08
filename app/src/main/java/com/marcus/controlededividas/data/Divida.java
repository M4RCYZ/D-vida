package com.marcus.controlededividas.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "dividas_table")
public class Divida {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String descricao;
    private double valor;
    private long data;
    private int userId; // Campo adicionado

    // Construtor vazio necessário para o Room
    public Divida() {}

    // Construtor principal que usaremos no nosso código
    public Divida(String descricao, double valor, int userId) {
        this.descricao = descricao;
        this.valor = valor;
        this.userId = userId; // Parâmetro e atribuição adicionados
        this.data = new Date().getTime();
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    // Getters e Setters para o novo campo userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(this.data));
    }
}