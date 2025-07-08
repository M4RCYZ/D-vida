package com.marcus.controlededividas.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DividaDao {

    @Insert
    void insert(Divida divida);

    @Query("SELECT * FROM dividas_table WHERE userId = :userId ORDER BY data DESC")
    LiveData<List<Divida>> getAllDividas(int userId);

    @Query("SELECT SUM(valor) FROM dividas_table WHERE userId = :userId")
    LiveData<Double> getTotalDivida(int userId);

    @Query("DELETE FROM dividas_table WHERE id = :dividaId")
    void deleteById(int dividaId);
}