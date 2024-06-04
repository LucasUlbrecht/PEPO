package com.example.trab1progmov;

import android.content.Context;
import android.util.Log;

public class Music {
    private String name;
    private int photoId;
    private int songId;
    private int letterId;

    public Music(Context context, String name) {
        this.name = name;
        this.photoId = getPhotoResourceId(context);
        this.songId = getSongResourceId(context);
        this.letterId = getLetterResourceId(context);
    }

    public String getName() {
        return name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getSongId() {
        return songId;
    }

    public int getLetterId() {
        return letterId;
    }

    private int getPhotoResourceId(Context context) {
        String imageName = name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Log.d("photo", imageName);
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    private int getSongResourceId(Context context) {
        String songName = name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Log.d("song", songName);
        return context.getResources().getIdentifier(songName, "raw", context.getPackageName());
    }

    public void setSongId(int songId) {
        this.songId=songId;
    }

    public int getLetterResourceId(Context context) {
        String songName = name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Log.d("song", songName);
        return context.getResources().getIdentifier(songName, "raw", context.getPackageName());
    }
}
