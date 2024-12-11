package com.example.mpprojectmtvtracker.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.concurrency.AppExecutors;
import com.example.mpprojectmtvtracker.dao.MovieDao;
import com.example.mpprojectmtvtracker.entity.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MovieRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private MovieDao movieDao;
    private List<Movie> allMoviesCache;

    private final ExecutorService executorService;

    // creating a constructor for our variables
    // and passing the variables to it.
    public MovieRepository(Context application, Callback<List<Movie>> callback) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDao();
        this.executorService = AppExecutors.getExecutorService();

        executorService.execute(() -> {
            allMoviesCache = movieDao.getAllMovies();
            callback.onResult(allMoviesCache);
        });
    }

    // creating a method to insert the data to our database.
    public void insert(Movie model) {
        executorService.execute(() -> movieDao.insert(model));
    }

    // creating a method to update data in database.
    public void update(Movie model) {
        executorService.execute(() -> movieDao.update(model));
    }

    // creating a method to delete the data in our database.
    public void delete(Movie model) {
        executorService.execute(() -> movieDao.delete(model));
    }

    // below is the method to delete all the courses.
    public void deleteAllMovies() {
        executorService.execute(() -> movieDao.deleteAllMovies());
    }

    // below method is to read all the courses.
    public List<Movie> getAllMovies() {
        return allMoviesCache;
    }
    // Callback interface
    public interface Callback<T> {
        void onResult(T result);
    }
}