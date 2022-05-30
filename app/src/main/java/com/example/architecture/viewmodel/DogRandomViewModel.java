package com.example.architecture.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.architecture.models.DogRandomResponses;
import com.example.architecture.models.DogRepository;

public class DogRandomViewModel extends AndroidViewModel {

    private DogRepository dogRepository = new DogRepository();
    private LiveData<DogRandomResponses> dogRandomResponsesLiveData;

    public LiveData<DogRandomResponses> getDogRandomResponsesLiveData() {
        return dogRandomResponsesLiveData;
    }

    public DogRandomViewModel(@NonNull Application application) {
        super(application);
        dogRandomResponsesLiveData = dogRepository.getDogLiveData();
    }

    public void fetchDogData() {
        dogRandomResponsesLiveData = dogRepository.getRandomDogData();
    }
}
