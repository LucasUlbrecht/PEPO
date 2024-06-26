package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trab1progmov.ORM.Entity.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM User WHERE email = :email AND senha = :senha LIMIT 1")
    User getUserByEmailAndPassword(String email, String senha);
    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);
}
