package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trab1progmov.ORM.Entity.Autor;
import com.example.trab1progmov.ORM.Entity.AutorWithGenre;

@Dao
public interface AutorDao {
    @Insert
    void insert(Autor autor);

    @Query("SELECT * FROM Autor WHERE id = :autorId")
    Autor getAutorById(int autorId);

    @Transaction
    @Query("SELECT * FROM Autor WHERE id = :autorId")
    AutorWithGenre getAutorWithGenres(int autorId);
}
