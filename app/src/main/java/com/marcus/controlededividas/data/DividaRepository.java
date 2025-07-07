package com.marcus.controlededividas.data;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DividaRepository {

    private DividaDao mDividaDao;
    private LiveData<List<Divida>> mAllDividas;
    private LiveData<Double> mTotalDivida;

    public DividaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDividaDao = db.dividaDao();
        mAllDividas = mDividaDao.getAllDividas();
        mTotalDivida = mDividaDao.getTotalDivida();
    }

    public LiveData<List<Divida>> getAllDividas() {
        return mAllDividas;
    }

    public LiveData<Double> getTotalDivida() {
        return mTotalDivida;
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
}