package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trab1progmov.ORM.Entity.Music;

@Dao
public interface MusicDao {
    @Insert
    void insert(Music music);

    @Query("SELECT * FROM Music WHERE id = :musicId")
    Music getMusicById(int musicId);
}
