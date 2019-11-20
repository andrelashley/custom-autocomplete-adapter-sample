package com.andrelashley.customautocompleteadaptersample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.andrelashley.customautocompleteadaptersample.adapters.SearchItemArrayAdapter;
import com.andrelashley.customautocompleteadaptersample.models.Photo;
import com.andrelashley.customautocompleteadaptersample.services.PhotoService;
import com.andrelashley.customautocompleteadaptersample.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mProgressLayout;
    private SearchItemArrayAdapter mAdapter;
    private AutoCompleteTextView mAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAutoComplete = findViewById(R.id.autocomplete);
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

                ArrayList<Photo> photos = new ArrayList<>();
                for (Photo photo : response.body()) {
                    photos.add(photo);
                }
                mAdapter
                        = new SearchItemArrayAdapter(
                        getApplicationContext(), R.layout.autocomplete_layout, photos);
                mAutoComplete.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                mProgressLayout.setVisibility(View.GONE);
            }
        });
    }
}
