package com.example.trab1progmov.ORM.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trab1progmov.ORM.Dao.AutorDao;
import com.example.trab1progmov.ORM.Dao.Autor_GenreDao;
import com.example.trab1progmov.ORM.Dao.GenreDao;
import com.example.trab1progmov.ORM.Dao.MusicDao;
import com.example.trab1progmov.ORM.Dao.PlaylistDao;
import com.example.trab1progmov.ORM.Dao.TrackDao;
import com.example.trab1progmov.ORM.Dao.UserDao;
import com.example.trab1progmov.ORM.Entity.*;
import com.example.trab1progmov.ORM.Entity.TypeConverter.GenresConverter;

@Database(entities = {Autor.class, Genres.class, Autor_Genre.class, Music.class, Playlist.class, Track.class, User.class}, version = 1)
@TypeConverters({GenresConverter.class, TrackConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract GenreDao genresDao();
    public abstract Autor_GenreDao autorGenreDao();
    public abstract MusicDao musicDao();
    public abstract PlaylistDao playlistDao();
    public abstract TrackDao trackDao();
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
