package com.andrelashley.customautocompleteadaptersample.services;

import com.andrelashley.customautocompleteadaptersample.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoService {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}
