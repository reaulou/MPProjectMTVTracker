package com.example.mpprojectmtvtracker.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TMDBApiService {
    @GET("search/movie")
    Call<MovieResponse> getPopularMovies(
            @Header("Authorization") String authorization,
            @Query("query") String name
    );
}