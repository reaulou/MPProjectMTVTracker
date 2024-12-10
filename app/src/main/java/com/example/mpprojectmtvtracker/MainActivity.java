package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpprojectmtvtracker.dto.MovieDto;
import com.example.mpprojectmtvtracker.entity.Movie;
import com.example.mpprojectmtvtracker.service.TMDBService;
import com.example.mpprojectmtvtracker.util.TokenManager;
import com.example.mpprojectmtvtracker.viewmodel.MovieViewModel;

import java.util.ArrayList;
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

        EditText tokenInput = findViewById(R.id.token_input);
        Button submitButton = findViewById(R.id.submit_button);

        // get access token
        TokenManager tokenManager = TokenManager.getInstance("dummytoken123");
        TMDBService serviceTMDB = new TMDBService();
        // Save token on button click and view
        submitButton.setOnClickListener(v -> {
            String token = tokenInput.getText().toString().trim();
            if (!token.isEmpty()) {
                tokenManager.setAccessToken(token);
                serviceTMDB.setAccessToken(token);
                System.out.println("[debugjuan]Token: " + token);
                Toast.makeText(this, "Token saved securely!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a valid token.", Toast.LENGTH_SHORT).show();
            }
        });

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
        System.out.println("debugjuan123");
    }
}