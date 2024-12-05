package com.example.mpprojectmtvtracker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpprojectmtvtracker.DTO.MovieDto;
import com.example.mpprojectmtvtracker.Entity.Movie;
import com.example.mpprojectmtvtracker.Service.TMDBService;
import com.example.mpprojectmtvtracker.ViewModel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TMDBService serviceTMDB = new TMDBService();
        serviceTMDB.searchMovieByName("Jack+Reacher").observe(this, new Observer<List<MovieDto>>() {
            @Override
            public void onChanged(List<MovieDto> movieDtos) {
                for(MovieDto movieDto : movieDtos){
                    System.out.println(movieDto.getId());
                    System.out.println("TMDBService: " + movieDto.getTitle());
                }
            }
        });

        // view model setup
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        Movie mov1 = new Movie("Interstellar", "Space Movie", "watched");
        //movieViewModel.deleteAllMovies();
        movieViewModel.insert(mov1);

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> models) {
                for(Movie movie : models) {
                    System.out.println(movie.getId());
                    System.out.println(movie.getMovieName());
                    System.out.println(movie.getMovieDescription());
                }
            }
        });
    }
}