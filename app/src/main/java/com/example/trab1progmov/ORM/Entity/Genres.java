package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Genres {
    @PrimaryKey(autoGenerate = true)
    private int genreId;
    private String description;

    @Override
    public String toString() {
        return "Genres{" +
                "id=" + genreId +
                ", description='" + description + '\'' +
                '}';
    }

    public Genres(int genreId, String description) {
        this.genreId = genreId;
        this.description = description;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
