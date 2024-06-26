package com.example.trab1progmov.ORM.Entity;
import com.example.trab1progmov.ORM.Entity.TypeConverter.GenresConverter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.ArrayList;

@Entity
@TypeConverters(GenresConverter.class)
public class Autor {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int followers;
    private String linkPhoto;
    private ArrayList<Genres> genres;
    private int popularity;
    private String type;
    private String uri;
    @Ignore
    public Autor(int id, String name, int followers, String linkPhoto) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.linkPhoto = linkPhoto;
    }

    public Autor(int id, String name, int followers, String linkPhoto, ArrayList<Genres> genres, int popularity, String type, String uri) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.linkPhoto = linkPhoto;
        this.genres = genres;
        this.popularity = popularity;
        this.type = type;
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", followers=" + followers +
                ", linkPhoto='" + linkPhoto + '\'' +
                ", genres=" + genres +
                ", popularity=" + popularity +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
