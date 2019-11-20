package com.andrelashley.customautocompleteadaptersample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.andrelashley.customautocompleteadaptersample.models.Photo;
import com.andrelashley.customautocompleteadaptersample.services.PhotoService;
import com.andrelashley.customautocompleteadaptersample.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mProgressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressLayout = findViewById(R.id.progressLayout);
        mProgressLayout.setVisibility(View.GONE);

        fetchPhotos();

    }

    private void fetchPhotos() {
        PhotoService photoService = ServiceBuilder.buildService(PhotoService.class);
        Call<List<Photo>> request = photoService.getPhotos();

        mProgressLayout.setVisibility(View.VISIBLE);
        request.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                mProgressLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                mProgressLayout.setVisibility(View.GONE);
            }
        });
    }
}
