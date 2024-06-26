package com.example.trab1progmov.ORM.Entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TrackConverter {
    @TypeConverter
    public String fromTrackList(List<Track> tracks) {
        if (tracks == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {}.getType();
        return gson.toJson(tracks, type);
    }

    @TypeConverter
    public List<Track> toTrackList(String trackString) {
        if (trackString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>() {}.getType();
        return gson.fromJson(trackString, type);
    }
}
