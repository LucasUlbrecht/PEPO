package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Genres {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;

    @Override
    public String toString() {
        return "Genres{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genres(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
