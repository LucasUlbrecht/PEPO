package com.example.trab1progmov.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ColorViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedColor = new MutableLiveData<>();

    public void setSelectedColor(int color) {
        selectedColor.setValue(color);
    }

    public LiveData<Integer> getSelectedColor() {
        return selectedColor;
    }
}