package com.example.trab1progmov.ORM.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trab1progmov.ORM.Dao.*;
import com.example.trab1progmov.ORM.Entity.*;
import com.example.trab1progmov.ORM.Entity.TypeConverter.GenresConverter;

@Database(entities = {Autor.class, Genres.class, Music.class, Playlist.class, Track.class, User.class, Autor_Genre.class}, version = 1)
@TypeConverters({TrackConverter.class, GenresConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract GenreDao genresDao();
    public abstract MusicDao musicDao();
    public abstract PlaylistDao playlistDao();
    public abstract TrackDao trackDao();
    public abstract UserDao userDao();
}
