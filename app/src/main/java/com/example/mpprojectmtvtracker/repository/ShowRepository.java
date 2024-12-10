package com.example.mpprojectmtvtracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mpprojectmtvtracker.AppDatabase;
import com.example.mpprojectmtvtracker.dao.ShowDao;
import com.example.mpprojectmtvtracker.entity.Show;

import java.util.List;

public class ShowRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private ShowDao showDao;
    private LiveData<List<Show>> allShows;

    // creating a constructor for our variables
    // and passing the variables to it.
    public ShowRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        showDao = database.showDao();
        allShows = showDao.getAllShows();
    }

    // creating a method to insert the data to our database.
    public void insert(Show model) {
        new Thread(() -> {
            try{
                showDao.insert(model);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    // creating a method to update data in database.
    public void update(Show model) {
        new Thread(() -> {
            try{
                showDao.update(model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // creating a method to delete the data in our database.
    public void delete(Show model) {
        new Thread(() -> {
            try{
                showDao.delete(model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // below is the method to delete all the courses.
    public void deleteAllShows() {
        new Thread(() -> {
            try{
                showDao.deleteAllShows();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // below method is to read all the courses.
    public LiveData<List<Show>> getAllShows() {
        return allShows;
    }
}