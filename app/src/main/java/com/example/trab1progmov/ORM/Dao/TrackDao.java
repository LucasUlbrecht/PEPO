package com.example.trab1progmov.ORM.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trab1progmov.ORM.Entity.Track;

@Dao
public interface TrackDao {
    @Insert
    void insert(Track track);

    @Query("SELECT * FROM Track WHERE id = :trackId")
    Track getTrackById(int trackId);
}
