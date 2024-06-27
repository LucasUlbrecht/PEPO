package com.example.trab1progmov.ORM.Entity.TypeConverter;

import androidx.room.TypeConverter;

import com.example.trab1progmov.ORM.Entity.Genres;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GenresConverter {
    @TypeConverter
    public String fromGenresList(ArrayList<Genres> genres) {
        if (genres == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Genres>>() {}.getType();
        return gson.toJson(genres, type);
    }

    @TypeConverter
    public ArrayList<Genres> toGenresList(String genresString) {
        if (genresString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Genres>>() {}.getType();
        return gson.fromJson(genresString, type);
    }
}
