package com.marcus.controlededividas.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DividaRepository {

    private final DividaDao mDividaDao;
    private final UserDao mUserDao; // Adicionamos o DAO de Usuário

    // O construtor agora inicializa os dois DAOs.
    public DividaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDividaDao = db.dividaDao();
        mUserDao = db.userDao();
    }

    public LiveData<List<Divida>> getAllDividas(int userId) {
        return mDividaDao.getAllDividas(userId);
    }

    public LiveData<Double> getTotalDivida(int userId) {
        return mDividaDao.getTotalDivida(userId);
    }

    public void insert(Divida divida) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mDividaDao.insert(divida);
        });
    }

    public void deleteById(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mDividaDao.deleteById(id);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserDao.getAllUsers();
    }

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }
}