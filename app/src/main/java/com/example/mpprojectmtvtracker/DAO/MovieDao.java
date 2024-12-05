package com.example.mpprojectmtvtracker.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mpprojectmtvtracker.Entity.Movie;

import java.util.List;

@androidx.room.Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table ORDER BY movieName ASC")
    LiveData<List<Movie>> getAllMovies();
}