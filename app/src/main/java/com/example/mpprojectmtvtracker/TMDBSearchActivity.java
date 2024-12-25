package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpprojectmtvtracker.dto.MovieDto;
import com.example.mpprojectmtvtracker.dto.MovieImageDto;
import com.example.mpprojectmtvtracker.entity.Movie;
import com.example.mpprojectmtvtracker.service.TMDBService;
import com.example.mpprojectmtvtracker.util.TokenManager;

import java.util.ArrayList;
import java.util.List;

public class TMDBSearchActivity extends AppCompatActivity {

    private Context context;

    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tmdbsearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        movies = new ArrayList<>();

        TMDBSearchActivityAdapter recyclerViewAdapter = new TMDBSearchActivityAdapter(this, movies);
        RecyclerView recyclerView = findViewById(R.id.tmdb_search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        TMDBService serviceTMDB = new TMDBService();
        SearchView simpleSearchView = (SearchView) findViewById(R.id.tmdb_search_search_view); // inititate a search view

        // perform set on query text listener event
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                TokenManager tokenManager = TokenManager.getInstance();
                String token = tokenManager.getAccessToken();

                serviceTMDB.setAccessToken(token);

                if(query != null && !query.isEmpty()){
                    query.replaceAll(" ", "+");
                }

                serviceTMDB.searchMovieByName(query).observe((AppCompatActivity)context, new Observer<List<MovieDto>>() {
                    @Override
                    public void onChanged(List<MovieDto> movieDtos) {
                        movies.clear();
                        for(MovieDto movieDto : movieDtos){

                            String name = movieDto.getTitle();
                            String desc = movieDto.getOverview();
                            String status = "unknown";

                            movies.add(new Movie(name, desc, status));

                            System.out.println(movieDto.getId());
                            System.out.println("TMDBService name: " + movieDto.getTitle());
                        }

//                        int sizeMovies = 0;
//                        if(!movies.isEmpty()){
//                            sizeMovies = movies.size();
//                        }

                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                serviceTMDB.getMovieImages(75780).observe((AppCompatActivity)context, new Observer<List<MovieImageDto>>() {
                    @Override
                    public void onChanged(List<MovieImageDto> movieImageDtos) {
                        for(MovieImageDto movieImageDto : movieImageDtos){
                            System.out.println("TMDBService path: " + movieImageDto.getFilePath());
                        }
                        System.out.println("testdebug999");
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }
}