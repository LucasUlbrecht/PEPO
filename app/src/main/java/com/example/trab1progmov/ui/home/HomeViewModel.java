package com.example.trab1progmov.ui.home;

import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<Boolean> isMusicPlaying;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        isMusicPlaying = new MutableLiveData<>();
        isMusicPlaying.setValue(false); // Inicialmente, a música não está tocando
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Boolean> getIsMusicPlaying() {
        return isMusicPlaying;
    }

    public void setMusicPlaying(boolean isPlaying) {
        isMusicPlaying.setValue(isPlaying);
    }
}
