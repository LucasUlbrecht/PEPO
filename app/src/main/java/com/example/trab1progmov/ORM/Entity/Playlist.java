package com.example.trab1progmov.ORM.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.trab1progmov.ORM.Entity.TrackConverter;

import java.util.List;

@Entity
public class Playlist {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean collaborative;
    private String description;
    private String spotifyUrl;
    private int followersCount;
    private String href;
    private String playlistId;
    private String name;
    private String ownerId;
    private String ownerName;
    private boolean isPublic;
    private String snapshotId;

    @TypeConverters(com.example.trab1progmov.ORM.Entity.TrackConverter.class)
    private List<Track> tracks;

    public Playlist(int id, boolean collaborative, String description, String spotifyUrl, int followersCount, String href, String playlistId, String name) {
        this.id = id;
        this.collaborative = collaborative;
        this.description = description;
        this.spotifyUrl = spotifyUrl;
        this.followersCount = followersCount;
        this.href = href;
        this.playlistId = playlistId;
        this.name = name;
    }

    public Playlist(int id, boolean collaborative, String description, String spotifyUrl, int followersCount, String href, String playlistId, String name, String ownerId, String ownerName, boolean isPublic, String snapshotId, List<Track> tracks) {
        this.id = id;
        this.collaborative = collaborative;
        this.description = description;
        this.spotifyUrl = spotifyUrl;
        this.followersCount = followersCount;
        this.href = href;
        this.playlistId = playlistId;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.isPublic = isPublic;
        this.snapshotId = snapshotId;
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", collaborative=" + collaborative +
                ", description='" + description + '\'' +
                ", spotifyUrl='" + spotifyUrl + '\'' +
                ", followersCount=" + followersCount +
                ", href='" + href + '\'' +
                ", playlistId='" + playlistId + '\'' +
                ", name='" + name + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", isPublic=" + isPublic +
                ", snapshotId='" + snapshotId + '\'' +
                ", tracks=" + tracks +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCollaborative() {
        return collaborative;
    }

    public void setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
