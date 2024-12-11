package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mpprojectmtvtracker.dao.MovieDao;
import com.example.mpprojectmtvtracker.dao.ShowDao;
import com.example.mpprojectmtvtracker.dao.UserDao;
import com.example.mpprojectmtvtracker.entity.Movie;
import com.example.mpprojectmtvtracker.entity.Show;
import com.example.mpprojectmtvtracker.entity.User;

@Database(entities = {User.class, Movie.class, Show.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UserDao userDao();
    public abstract MovieDao movieDao();
    public abstract ShowDao showDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database_1")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // roomCallback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new AppDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AppDatabase instance) {
            UserDao userDao = instance.userDao();
            MovieDao movieDao = instance.movieDao();
            ShowDao showDao = instance.showDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}


