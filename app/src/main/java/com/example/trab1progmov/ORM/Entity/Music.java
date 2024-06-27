package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity (foreignKeys = @ForeignKey(entity = Autor.class,
        parentColumns = "autorId",
        childColumns = "idArtist",
        onDelete = ForeignKey.CASCADE))
public class Music {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int idArtist;
    private String linkPhoto;

    public Music(int id, String title, int idArtist, String linkPhoto) {
        this.id = id;
        this.title = title;
        this.idArtist = idArtist;
        this.linkPhoto = linkPhoto;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", idArtist=" + idArtist +
                ", linkPhoto='" + linkPhoto + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }
    @Ignore
    public Music(int id, String title) {
        this.title = title;
    }
}
