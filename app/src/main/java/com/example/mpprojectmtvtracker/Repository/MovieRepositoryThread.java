package com.example.mpprojectmtvtracker.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.DAO.MovieDao;
import com.example.mpprojectmtvtracker.Entity.Movie;

import java.util.List;

public class MovieRepositoryThread {

    // below line is the create a variable
    // for dao and list for all courses.
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    // creating a constructor for our variables
    // and passing the variables to it.
    public MovieRepositoryThread(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    // creating a method to insert the data to our database.
    public void insert(Movie model) {
        new Thread(() -> {
            try{
                movieDao.insert(model);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    // creating a method to update data in database.
    public void update(Movie model) {
        new Thread(() -> {
            try{
                movieDao.update(model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // creating a method to delete the data in our database.
    public void delete(Movie model) {
        new Thread(() -> {
            try{
                movieDao.delete(model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // below is the method to delete all the courses.
    public void deleteAllMovies() {
        new Thread(() -> {
            try{
                movieDao.deleteAllMovies();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // below method is to read all the courses.
    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}