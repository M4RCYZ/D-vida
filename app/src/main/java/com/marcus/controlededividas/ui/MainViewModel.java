package com.marcus.controlededividas.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marcus.controlededividas.data.Divida;
import com.marcus.controlededividas.data.DividaRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final DividaRepository mRepository;
    private final LiveData<List<Divida>> mAllDividas;
    private final LiveData<Double> mTotalDivida;

    public MainViewModel(@NonNull Application application) {
        super(application);
        // Cria o repositório, que por sua vez acessa o banco de dados.
        mRepository = new DividaRepository(application);
        // Pega os LiveData do repositório.
        mAllDividas = mRepository.getAllDividas();
        mTotalDivida = mRepository.getTotalDivida();
    }

    public LiveData<List<Divida>> getAllDividas() {
        return mAllDividas;
    }

    public LiveData<Double> getTotalDivida() {
        return mTotalDivida;
    }
    public void insert(Divida divida) {
        mRepository.insert(divida);
    }
    public void deleteById(int id) {
        mRepository.deleteById(id);
    }
}