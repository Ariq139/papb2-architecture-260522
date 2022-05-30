package com.example.architecture.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.architecture.services.ApiClient;
import com.example.architecture.services.DogService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {
    private DogService dogService;
    private MutableLiveData<DogRandomResponses> dogLiveData = new MutableLiveData<>();

    public MutableLiveData<DogRandomResponses> getDogLiveData() {
        return dogLiveData;
    }

    public DogRepository() {
        dogService = ApiClient.getRetrofitInstance().create(DogService.class);
    }

    public LiveData<DogRandomResponses> getRandomDogData() {
        dogService.fetchRandomDog().enqueue(new Callback<DogRandomResponses>() {
            @Override
            public void onResponse(Call<DogRandomResponses> call, Response<DogRandomResponses> response) {
                dogLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DogRandomResponses> call, Throwable t) {
                dogLiveData.setValue(null);
            }
        });

        return dogLiveData;
    }
}
