package com.example.trab1progmov.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ColorViewModel extends ViewModel {
    private final MutableLiveData<int[]> selectedColors = new MutableLiveData<>();

    public void setSelectedColors(int[] colors) {
        selectedColors.setValue(colors);
    }

    public LiveData<int[]> getSelectedColor() {
        return selectedColors;
    }
}
