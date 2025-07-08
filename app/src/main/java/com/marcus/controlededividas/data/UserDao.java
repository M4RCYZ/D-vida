package com.marcus.controlededividas.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users_table ORDER BY name ASC")
    LiveData<List<User>> getAllUsers();
}