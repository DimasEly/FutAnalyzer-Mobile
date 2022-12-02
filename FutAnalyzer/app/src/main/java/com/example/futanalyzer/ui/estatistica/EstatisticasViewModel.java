package com.example.futanalyzer.ui.estatistica;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstatisticasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstatisticasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}