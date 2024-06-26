package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trab1progmov.ORM.Entity.Playlist;

@Dao
public interface PlaylistDao {
    @Insert
    void insert(Playlist playlist);

    @Transaction
    @Query("SELECT * FROM Playlist WHERE id = :playlistId")
    Playlist getPlaylistById(int playlistId);
}
