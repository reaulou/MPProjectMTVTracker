package com.example.mpprojectmtvtracker.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApiService {
    @GET("search/movie")
    Call<MovieResponse> getMoviesWithName(
            @Header("Authorization") String authorization,
            @Query("query") String name
    );

    @GET("movie/{movie_id}/images")
    Call<MovieImageResponse> getMovieImages(
            @Header("Authorization") String authorization,
            @Path("movie_id") int movieId
    );
}