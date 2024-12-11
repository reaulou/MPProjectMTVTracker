package com.example.mpprojectmtvtracker.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mpprojectmtvtracker.entity.Show;

import java.util.List;

@androidx.room.Dao
public interface ShowDao {

    @Insert
    void insert(Show show);

    @Update
    void update(Show show);

    @Delete
    void delete(Show show);

    @Query("DELETE FROM show_table")
    void deleteAllShows();

    @Query("SELECT * FROM show_table")
    List<Show> getAllShows();
}