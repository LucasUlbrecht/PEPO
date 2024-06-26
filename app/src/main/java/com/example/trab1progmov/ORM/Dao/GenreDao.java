package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trab1progmov.ORM.Entity.Genres;

@Dao
public interface GenreDao {
    @Insert
    void insert(Genres genre);

    @Query("SELECT * FROM Genres WHERE id = :genreId")
    Genres getGenreById(int genreId);
}
