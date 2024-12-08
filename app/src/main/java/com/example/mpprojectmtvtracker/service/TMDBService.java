package com.example.mpprojectmtvtracker.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mpprojectmtvtracker.dto.MovieDto;
import com.example.mpprojectmtvtracker.network.MovieResponse;
import com.example.mpprojectmtvtracker.network.RetrofitInstance;
import com.example.mpprojectmtvtracker.network.TMDBApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TMDBService {

    TMDBApiService tmdbApiService;
    String accessToken;

    public TMDBService(){
        tmdbApiService = RetrofitInstance.getApiService();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LiveData<List<MovieDto>> searchMovieByName(String name){
        // retrofit
        MutableLiveData<List<MovieDto>> responseLiveData = new MutableLiveData<List<MovieDto>>();
        tmdbApiService.getPopularMovies("Bearer " + accessToken, name).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MovieDto> movieDtos = response.body().getResults();

                    responseLiveData.postValue(movieDtos);

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return responseLiveData;
    }

}
