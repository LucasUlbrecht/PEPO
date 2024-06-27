package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import com.example.trab1progmov.ORM.Entity.Autor_Genre;

import java.util.List;

@Dao
public interface Autor_GenreDao {
    @Insert
    void insert(Autor_Genre autorGenre);

    @Update
    void update(Autor_Genre autorGenre);

    @Delete
    void delete(Autor_Genre autorGenre);

    @Query("SELECT * FROM Autor_Genre WHERE autorId = :autorId AND genreId = :genreId")
    Autor_Genre getAutorGenreByIds(int autorId, int genreId);

    @Query("SELECT * FROM Autor_Genre")
    List<Autor_Genre> getAllAutorGenres();
}
