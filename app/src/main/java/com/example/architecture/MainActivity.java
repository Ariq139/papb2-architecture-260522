package com.example.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.architecture.viewmodel.DogRandomViewModel;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mRandomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image_view);
        mRandomButton = findViewById(R.id.random_btn);

        DogRandomViewModel dogRandomViewModel = new DogRandomViewModel(getApplication());
        observeDogVM(dogRandomViewModel);

        mRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogRandomViewModel.fetchDogData();
            }
        });
    }

    public void observeDogVM (DogRandomViewModel vm){
        vm.getDogRandomResponsesLiveData().observe(this, dogRandomResponses -> {
            if (dogRandomResponses.getStatus().equals("success")) {
                Picasso.get().load(dogRandomResponses.getMessage()).into(mImageView);
            }
        });
    }
}