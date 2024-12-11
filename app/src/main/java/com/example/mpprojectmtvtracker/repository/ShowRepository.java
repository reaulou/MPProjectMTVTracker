package com.example.mpprojectmtvtracker.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.concurrency.AppExecutors;
import com.example.mpprojectmtvtracker.dao.ShowDao;
import com.example.mpprojectmtvtracker.entity.Show;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowRepository{

    // below line is the create a variable
    // for dao and list for all courses.
    private ShowDao showDao;
    private List<Show> allShowsCache;

    private final ExecutorService executorService;

    // creating a constructor for our variables
    // and passing the variables to it.
    public ShowRepository(Context application, Callback<List<Show>> callback) {
        AppDatabase database = AppDatabase.getInstance(application);
        showDao = database.showDao();
        this.executorService = AppExecutors.getExecutorService();

        executorService.execute(() -> {
            allShowsCache = showDao.getAllShows(); // Fetch from DB
            callback.onResult(allShowsCache); // Notify the UI
        });
    }

    // creating a method to insert the data to our database.
    public void insert(Show model) {
        executorService.execute(() -> showDao.insert(model));
    }

    // creating a method to update data in database.
    public void update(Show model) {
        executorService.execute(() -> showDao.update(model));
    }

    // creating a method to delete the data in our database.
    public void delete(Show model) {
        executorService.execute(() -> showDao.delete(model));
    }

    // below is the method to delete all the courses.
    public void deleteAllShows() {
        executorService.execute(() -> showDao.deleteAllShows());
    }

    // below method is to read all the courses.
    public List<Show> getAllShows() {
        return allShowsCache;
    }

    public interface Callback<T> {
        void onResult(T result);
    }
}