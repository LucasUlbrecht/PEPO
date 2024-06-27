package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trab1progmov.ORM.Entity.TypeConverter.GenresConverter;

import java.util.ArrayList;

@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    private int autorId;
    private String name;
    private int followers;
    private String linkPhoto;
    @TypeConverters(GenresConverter.class)
    private ArrayList<Genres> genres;
    private int popularity;
    private String type;
    private String uri;

    // Construtor público vazio
    public Autor() {}

    // Construtor com todos os campos
    public Autor(int id, String name, int followers, String linkPhoto, ArrayList<Genres> genres, int popularity, String type, String uri) {
        this.autorId = id;
        this.name = name;
        this.followers = followers;
        this.linkPhoto = linkPhoto;
        this.genres = genres;
        this.popularity = popularity;
        this.type = type;
        this.uri = uri;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int id) {
        this.autorId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
