package com.example.mpprojectmtvtracker.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String movieName;
    private String movieDescription;
    private String status;

    public Movie(String movieName, String movieDescription, String status) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}