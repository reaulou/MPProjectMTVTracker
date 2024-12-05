package com.example.mpprojectmtvtracker.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.Entity.Movie;
import com.example.mpprojectmtvtracker.Repository.MovieRepository;
import com.example.mpprojectmtvtracker.Repository.MovieRepositoryThread;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    // creating a new variable for course repository.
    private MovieRepositoryThread repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<Movie>> allMovies;

    // constructor for our view modal.
    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepositoryThread(application);
        allMovies = repository.getAllMovies();
    }

    // below method is use to insert the data to our repository.
    public void insert(Movie model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(Movie model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(Movie model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllMovies() {
        repository.deleteAllMovies();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}