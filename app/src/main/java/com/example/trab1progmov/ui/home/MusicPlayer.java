package com.example.trab1progmov.ui.home;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import com.example.trab1progmov.Music;
import com.example.trab1progmov.MusicPlayerListener;

import java.util.ArrayList;
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


    public void setCurrentMusic(Music music){
        currentMusic=music;
    }
    public static synchronized MusicPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new MusicPlayer(context);
        }
        return instance;
    }
    public void play(){
        play(this.currentMusic);
    }

    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void play(Music selectedMusic) {
        Log.d("playrequire", "playrequire");

        // Verifica se o MediaPlayer está tocando
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            // Verifica se a música selecionada é a mesma que está sendo reproduzida
            if (currentMusic.getName().equals(selectedMusic.getName())) {
                // Se sim, pausa a reprodução
                pause();
                return;
            } else {
                // Se não for a mesma música, para a reprodução atual e libera o MediaPlayer
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        // Inicializa o MediaPlayer com a nova música
        int rawId = selectedMusic.getSongId();
        currentMusic = selectedMusic;
        mediaPlayer = MediaPlayer.create(applicationContext, rawId);

        if (mediaPlayer != null) {
            // Inicia a reprodução da nova música
            mediaPlayer.start();
            Log.d("TAG", "A música está tocando.");
            notifyMusicStarted(selectedMusic);
        } else {
            // Falha ao criar o MediaPlayer
            Log.e("error", "Falha ao criar o MediaPlayer.");
        }
    }





    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.d("TAG", "A reprodução foi pausada.");
            notifyMusicPaused();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.seekTo(0);
    }
    public boolean isEmpty(){ return mediaPlayer==null; }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getMediaDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentMediaPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public Music getMusic() {
        return currentMusic;
    }

    private boolean isPlayingSafe() {
        try {
            return mediaPlayer.isPlaying();
        } catch (IllegalStateException e) {
            // Trate a exceção aqui, se necessário
            Log.e("MediaPlayer", "Erro ao verificar se está tocando", e);
            return false;
        }
    }


}
