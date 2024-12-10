package com.example.mpprojectmtvtracker.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "show_table")
public class Show {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String showName;
    private String showDescription;
    private String status;

    public Show(String showName, String showDescription, String status) {
        this.showName = showName;
        this.showDescription = showDescription;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}