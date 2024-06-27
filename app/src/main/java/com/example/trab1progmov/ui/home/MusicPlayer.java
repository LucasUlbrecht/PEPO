package com.example.trab1progmov.ui.home;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.trab1progmov.Music;
import com.example.trab1progmov.MusicPlayerListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MusicPlayer {
    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;
    private Context applicationContext;
    private Music currentMusic;
    private List<MusicPlayerListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(MusicPlayerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MusicPlayerListener listener) {
        listeners.remove(listener);
    }

    public void clearListeners() {
        listeners.clear();
    }

    private void notifyMusicStarted(Music music) {
        for (MusicPlayerListener listener : listeners) {
            listener.onMusicStarted(music);
        }
    }

    private void notifyMusicPaused() {
        for (MusicPlayerListener listener : listeners) {
            listener.onMusicPaused();
        }
    }

    private void notifyMusicStopped() {
        for (MusicPlayerListener listener : listeners) {
            listener.onMusicStopped();
        }
    }

    private MusicPlayer(Context context) {
        applicationContext = context.getApplicationContext();
    }

    public void setCurrentMusic(Music music) {
        currentMusic = music;
    }

    public static synchronized MusicPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new MusicPlayer(context);
        }
        return instance;
    }

    public void play() {
        play(this.currentMusic);
    }

    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void play(Music selectedMusic) {
        Log.d("playrequire", "playrequire");

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            if (currentMusic.getName().equals(selectedMusic.getName())) {
                pause();
                return;
            } else {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        int rawId = selectedMusic.getSongId();
        currentMusic = selectedMusic;
        mediaPlayer = MediaPlayer.create(applicationContext, rawId);

        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d("TAG", "A música está tocando.");
            notifyMusicStarted(selectedMusic);
        } else {
            Log.e("error", "Falha ao criar o MediaPlayer.");
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.d("TAG", "A reprodução foi pausada.");
            notifyMusicPaused();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seekTo(0);
        }
    }

    public boolean isEmpty() {
        return mediaPlayer == null;
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getMediaDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        } else {
            return 0;
        }
    }

    public int getCurrentMediaPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public Music getMusic() {
        return currentMusic;
    }

    private boolean isPlayingSafe() {
        try {
            return mediaPlayer != null && mediaPlayer.isPlaying();
        } catch (IllegalStateException e) {
            Log.e("MediaPlayer", "Erro ao verificar se está tocando", e);
            return false;
        }
    }
}
