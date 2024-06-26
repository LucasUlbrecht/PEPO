package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"autorId", "genreId"})
public class Autor_Genre {
        public int autorId;
        public int genreId;

        // Getters e Setters

        @Override
        public String toString() {
                return "Autor_Genre{" +
                        "autorId=" + autorId +
                        ", genreId=" + genreId +
                        '}';
        }

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
