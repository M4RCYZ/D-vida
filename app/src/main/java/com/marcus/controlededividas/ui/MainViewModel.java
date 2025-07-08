package com.marcus.controlededividas.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.marcus.controlededividas.data.Divida;
import com.marcus.controlededividas.data.DividaRepository;
import com.marcus.controlededividas.data.User;

import java.util.Collections;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final DividaRepository mRepository;

    private final MutableLiveData<Integer> currentUserId = new MutableLiveData<>();

    private final LiveData<List<User>> mAllUsers;

    private final LiveData<List<Divida>> mAllDividas;
    private final LiveData<Double> mTotalDivida;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DividaRepository(application);

        mAllUsers = mRepository.getAllUsers();

        mAllDividas = Transformations.switchMap(currentUserId, id -> {
            if (id == null) {
                return new MutableLiveData<>(Collections.emptyList());
            }
            return mRepository.getAllDividas(id);
        });

        mTotalDivida = Transformations.switchMap(currentUserId, id -> {
            if (id == null) {
                return new MutableLiveData<>(0.0);
            }
            return mRepository.getTotalDivida(id);
        });
    }

    public void setCurrentUser(int userId) {
        currentUserId.setValue(userId);
    }

    public LiveData<List<Divida>> getDividasDoUsuario() { return mAllDividas; }
    public LiveData<Double> getTotalDaDividaDoUsuario() { return mTotalDivida; }
    public LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void insert(Divida divida) { mRepository.insert(divida); }
    public void deleteById(int id) { mRepository.deleteById(id); }
    public void insertUser(User user) { mRepository.insertUser(user); }
}