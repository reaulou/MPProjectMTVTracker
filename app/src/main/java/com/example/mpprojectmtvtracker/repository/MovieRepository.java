package com.example.mpprojectmtvtracker.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.dao.MovieDao;
import com.example.mpprojectmtvtracker.entity.Movie;

import java.util.List;

public class MovieRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    // creating a constructor for our variables
    // and passing the variables to it.
    public MovieRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    // creating a method to insert the data to our database.
    public void insert(Movie model) {
        new InsertMovieAsyncTask(movieDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Movie model) {
        new UpdateMovieAsyncTask(movieDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Movie model) {
        new DeleteMovieAsyncTask(movieDao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllMovies() {
        new DeleteAllMoviesAsyncTask(movieDao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    // we are creating a async task method to insert new course.
    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... model) {
            // below line is use to insert our modal in dao.
            movieDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private UpdateMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... models) {
            // below line is use to update
            // our modal in dao.
            movieDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... models) {
            // below line is use to delete
            // our course modal in dao.
            movieDao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;
        private DeleteAllMoviesAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAllMovies();
            return null;
        }
    }
}