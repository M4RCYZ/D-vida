package com.marcus.controlededividas.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public User(String name) {
        this.name = name;
    }

    // Usado pelo Spinner para mostrar o nome do usuário na lista
    @Override
    public String toString() {
        return name;
    }
}