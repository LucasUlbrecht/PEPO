package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"autorId", "genreId"},
        foreignKeys = {
                @ForeignKey(entity = Autor.class,
                        parentColumns = "autorId",
                        childColumns = "autorId"),
                @ForeignKey(entity = Genres.class,
                        parentColumns = "genreId",
                        childColumns = "genreId")
        })
public class Autor_Genre {
        public int autorId;
        public int genreId;

        public Autor_Genre(int autorId, int genreId) {
                this.autorId = autorId;
                this.genreId = genreId;
        }

        public int getAutorId() {
                return autorId;
        }

        public void setAutorId(int autorId) {
                this.autorId = autorId;
        }

        public int getGenreId() {
                return genreId;
        }

        public void setGenreId(int genreId) {
                this.genreId = genreId;
        }
}
