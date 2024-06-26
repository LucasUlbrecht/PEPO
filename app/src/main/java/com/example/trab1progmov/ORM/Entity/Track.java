package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Track {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String trackName;
    private String trackHref;
    private String albumName;
    private String albumHref;

    public Track(int id, String trackName, String trackHref, String albumName, String albumHref) {
        this.id = id;
        this.trackName = trackName;
        this.trackHref = trackHref;
        this.albumName = albumName;
        this.albumHref = albumHref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackHref() {
        return trackHref;
    }

    public void setTrackHref(String trackHref) {
        this.trackHref = trackHref;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumHref() {
        return albumHref;
    }

    public void setAlbumHref(String albumHref) {
        this.albumHref = albumHref;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", trackName='" + trackName + '\'' +
                ", trackHref='" + trackHref + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumHref='" + albumHref + '\'' +
                '}';
    }
}
