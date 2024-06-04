package com.example.trab1progmov.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {
    private final MutableLiveData<Integer> mSelectedItemIndex;

    public NotificationsViewModel() {
        mSelectedItemIndex = new MutableLiveData<>();
        mSelectedItemIndex.setValue(0); // Define o primeiro item como selecionado inicialmente
    }

    public LiveData<Integer> getSelectedItemIndex() {
        return mSelectedItemIndex;
    }

    public void setSelectedItemIndex(int index) {
        mSelectedItemIndex.setValue(index);
    }
}