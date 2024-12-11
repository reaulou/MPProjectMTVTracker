package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mpprojectmtvtracker.dto.MovieDto;
import com.example.mpprojectmtvtracker.entity.Movie;
import com.example.mpprojectmtvtracker.entity.Show;
import com.example.mpprojectmtvtracker.repository.MovieRepository;
import com.example.mpprojectmtvtracker.repository.ShowRepository;
import com.example.mpprojectmtvtracker.service.TMDBService;
import com.example.mpprojectmtvtracker.util.TokenManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("debugjuan123: Start main activity");
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
                System.out.println("debugjuan123: Token: " + token);
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

        MovieRepository movieRepository = new MovieRepository(context, movies->{
            runOnUiThread(()->{
                System.out.println("debugjuan123: " + movies.size());
                Toast.makeText(this, "Shows loaded: " + movies.size(), Toast.LENGTH_SHORT).show();
            });
        });
        //movieRepository.deleteAllMovies();
        Movie mov1 = new Movie("Interstellarioo", "Space Movie", "watched");
        movieRepository.insert(mov1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Movie> movies = movieRepository.getAllMovies();
        for(Movie movie : movies) {
            System.out.println(movie.getId());
            System.out.println(movie.getMovieName());
            System.out.println(movie.getMovieDescription());
        }

        // show code
        ShowRepository showRepository = new ShowRepository(context, shows -> {
            runOnUiThread(() -> {
                System.out.println("debugjuan123: " + shows.size());
                Toast.makeText(this, "Shows loaded: " + shows.size(), Toast.LENGTH_SHORT).show();
            });
        });

        Show show1 = new Show("ATLA", "Adventure show", "watched");
        showRepository.insert(show1);

        Show show2 = new Show("Cyberpunk", "Scifi Dystopia", "watched");
        showRepository.insert(show2);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Show> showss = showRepository.getAllShows();
        if(showss.size() > 6){
            showRepository.deleteAllShows();
            System.out.println("Show table exceeded 6 items, deleted all items");
        }
        for(Show show : showss) {
            System.out.println(show.getId());
            System.out.println(show.getShowName());
            System.out.println(show.getShowDescription());
        }
        int te = 0;
    }
}