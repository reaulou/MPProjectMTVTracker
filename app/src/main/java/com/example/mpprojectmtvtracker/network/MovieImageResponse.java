package com.example.mpprojectmtvtracker.network;

import com.example.mpprojectmtvtracker.dto.MovieDto;
import com.example.mpprojectmtvtracker.dto.MovieImageDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieImageResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("logos")
    private List<MovieImageDto> results;

    public List<MovieImageDto> getResults() {
        return results;
    }

    public void setResults(List<MovieImageDto> results) {
        this.results = results;
    }

}
