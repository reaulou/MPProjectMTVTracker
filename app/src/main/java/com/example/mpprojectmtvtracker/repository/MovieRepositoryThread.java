package com.example.mpprojectmtvtracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.concurrency.AppExecutors;
import com.example.mpprojectmtvtracker.dao.MovieDao;
import com.example.mpprojectmtvtracker.entity.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieRepositoryThread {

    // below line is the create a variable
    // for dao and list for all courses.
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;
    private final ExecutorService executorService;

    // creating a constructor for our variables
    // and passing the variables to it.
    public MovieRepositoryThread(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
        this.executorService = AppExecutors.getExecutorService();

        // get all movies
        executorService.execute(() -> {
            allMovies = movieDao.getAllMovies();
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
    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
    // Callback interface
    public interface Callback<T> {
        void onResult(T result);
    }
}