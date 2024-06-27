package com.example.trab1progmov.ORM.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.Junction;

import java.util.List;

public class AutorWithGenre {
    @Embedded public Autor autor;
    @Relation(
            parentColumn = "autorId",
            entityColumn = "genreId",
            associateBy = @Junction(Autor_Genre.class)
    )
    public List<Genres> genres;

    // Getters e Setters

    @Override
    public String toString() {
        return "AutorWithGenre{" +
                "autor=" + autor +
                ", genres=" + genres +
                '}';
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public AutorWithGenre(Autor autor, List<Genres> genres) {
        this.autor = autor;
        this.genres = genres;
    }
}
